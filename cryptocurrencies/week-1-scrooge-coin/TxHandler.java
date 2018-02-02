import java.util.ArrayList;
import java.util.ListIterator;

public class TxHandler {

    private UTXOPool pool;

    /**
     * Creates a public ledger whose current UTXOPool (collection of unspent transaction outputs) is
     * {@code utxoPool}. This should make a copy of utxoPool by using the UTXOPool(UTXOPool uPool)
     * constructor.
     */
    public TxHandler(UTXOPool utxoPool) {
        this.pool = new UTXOPool(utxoPool);
    }

    /**
     * @return true if:
     * (1) all outputs claimed by {@code tx} are in the current UTXO pool, 
     * (2) the signatures on each input of {@code tx} are valid, 
     * (3) no UTXO is claimed multiple times by {@code tx},
     * (4) all of {@code tx}s output values are non-negative, and
     * (5) the sum of {@code tx}s input values is greater than or equal to the sum of its output
     *     values; and false otherwise.
     */
    public boolean isValidTx(Transaction tx) {
        ArrayList<UTXO> claimedOutputs = new ArrayList<UTXO>();
        double totalInput = 0;

        ListIterator<Transaction.Input> inputs = tx.getInputs().listIterator();
        while (inputs.hasNext()) {
            int index = inputs.nextIndex();
            Transaction.Input input = inputs.next();
            UTXO utxo = new UTXO(input.prevTxHash, input.outputIndex);

            // verify rule 1: each input of this transaction must be the
            // unspent output of a previous transaction. If we can't find an
            // output in the pool, it means this input is invalid: either the
            // output has already been spent in another transaction (double
            // spending), or the input does not exist (the prevHash or the
            // outputIndex is invalid).
            Transaction.Output claimedOutput = this.pool.getTxOutput(utxo);
            if (claimedOutput == null) {
                return false;
            }

            // verify rule 2: the input must have been signed for the current
            // transaction.
            if (claimedOutput.address == null || input.signature == null) {
                return false;
            }
            boolean isSignatureValid = Crypto.verifySignature(
                // signer public key
                claimedOutput.address,
                // Data that has been signed, which is the concatenation of
                // this input with all the outputs of the transation. See
                // getRawDataToSign() for more details.
                tx.getRawDataToSign(index),
                // signature we're verifying
                input.signature);
            if (!isSignatureValid) {
                return false;
            }

            // verify rule 3: make sure that the output claimed by this input
            // has not already been claimed by another input in this
            // transaction (aka double-spending)
            if (claimedOutputs.contains(utxo)) {
                return false;
            }

            // now that we checked that this input is valid (it claims an
            // existing output and has a valid signature), we keep the
            // corresponding UTXO around to make sure other inputs won't claim
            // the same output in this transaction
            claimedOutputs.add(utxo);

            // update the inputs sum (used to verify rule 5 below)
            totalInput = totalInput + claimedOutput.value;
        }

        double totalOutput = 0;
        for (Transaction.Output output : tx.getOutputs()) {
            // verify rule 4: no output can be negative
            if (output.value < 0) {
                return false;
            }
            totalOutput = totalOutput + output.value;

            // verify rule 5: the sum of the inputs must be greater or equal to
            // the sum of the outputs
            if (totalInput < totalOutput) {
                return false;
            }
        }

        return true;
    }

    /**
     * Handles each epoch by receiving an unordered array of proposed transactions, checking each
     * transaction for correctness, returning a mutually valid array of accepted transactions, and
     * updating the current UTXO pool as appropriate.
     */
    public Transaction[] handleTxs(Transaction[] possibleTxs) {
        ArrayList<Transaction> validTxs = new ArrayList<Transaction>();

        for (Transaction tx : possibleTxs) {
            // if this transaction is not valid, just ignore it
            if (!this.isValidTx(tx)) {
                continue;
            }

            // this transaction is valid, add it to the set of transactions we'll return
            validTxs.add(tx);

            // remove all the outputs claimed by this transaction
            for (Transaction.Input input : tx.getInputs()) {
                UTXO utxo = new UTXO(input.prevTxHash, input.outputIndex);
                this.pool.removeUTXO(utxo);
            }

            // add all the outputs produced by the transaction
            ListIterator<Transaction.Output> outputs = tx.getOutputs().listIterator();
            while (outputs.hasNext()) {
                // the output index in the transaction is the actual index in the outputs list
                int index = outputs.nextIndex();
                Transaction.Output output = outputs.next();
                UTXO utxo = new UTXO(tx.getHash(), index);
                this.pool.addUTXO(utxo, output);
            }

        }

        Transaction[] res = new Transaction[validTxs.size()];
        return validTxs.toArray(res);
    }
}
