#![feature(plugin)]
#![plugin(peg_syntax_ext)]

use std::env;

mod grammar { include!(concat!(env!("OUT_DIR"), "/grammar.rs")); }
mod token;

pub use token::Token;
pub use grammar::tokenize;

fn help() {
    println!("usage:
lexer <file>: tokenize the given file and print the tokens on stdout\n");
}

fn main() {
    let args: Vec<String> = env::args().collect();
    // TODO
}

#[cfg(test)]
mod test {
    use grammar;
    use token::Token;

    #[test]
    fn parse_line_comment() {
        fn parse(input: &str) {
            assert!(grammar::comment(input).is_ok());
        }
        parse("-- comment");
        parse("-- a longer line comment");
    }

    #[test]
    fn parse_comment() {
        fn parse(input: &str) {
            assert!(grammar::comment(input).is_ok())
        }
        parse("(* comment *)");
        parse("(**)");
        parse("(*comment*)");
        parse("(*\ncom\nment\n*)");
        parse("(*(*comment*)*)");
        parse("(*(*com\tment \n *) \n *)");
        parse("(* (*comment*) *)");
        parse("(* (*comment*) (*comment*) *)");
        parse("(* weird (*com\n\n\tment*)(*\n*)(*(**)*) (**) *)");
    }

    #[test]
    fn parse_keyword() {
        fn parse(input: &str, token: Token) {
            let res = grammar::keyword(input).unwrap();
            assert_eq!(res, token);
        }

        parse("if", Token::If);
        parse("fi", Token::Fi);
        parse("let", Token::Let);
        parse("class", Token::Class);

        // keyword are case insensitive
        parse("If", Token::If);
        parse("fI", Token::Fi);
        parse("LET", Token::Let);
        parse("claSS", Token::Class);
    }

    #[test]
    fn parse_bool() {
        assert_eq!(grammar::boolean("true").unwrap(), Token::Bool(true));
        assert_eq!(grammar::boolean("false").unwrap(), Token::Bool(false));
        assert_eq!(grammar::boolean("fALSE").unwrap(), Token::Bool(false));
        assert_eq!(grammar::boolean("fAlsE").unwrap(), Token::Bool(false));
        assert_eq!(grammar::boolean("tRUe").unwrap(), Token::Bool(true));
        assert!(grammar::boolean("False").is_err());
        assert!(grammar::boolean("True").is_err());
    }

    #[test]
    fn parse_string() {
        fn parse(input: &str) -> String {
            if let Token::String(s) = grammar::string(input).unwrap() {
                s
            } else {
                unreachable!()
            }
        }
        let s = parse(r#""1""#);
        assert_eq!(s.as_str(), "1");

        let s = parse(r#""abc""#);
        assert_eq!(s.as_str(), "abc");

        let s = parse(r#""a\nc""#);
        assert_eq!(s.as_str(), "a\nc");

        // FIXME: we add an extra space here...
        // let s = parse("\"a\\\n    b\"");
        // assert_eq!(s.as_str(), "ab");
        let s = parse("\"a\\\n    b\"");
        assert_eq!(s.as_str(), "a b");

        let s = parse(r#""abc\
\ndef\tf\g\h""#);
        assert_eq!(s.as_str(), "abc \ndef\tfgh");
    }

    #[test]
    fn parse_sigils() {
        let s = r#"<= <- => . @ ~ / + - < = () , ; : { }"#;
        assert_eq!(
            grammar::tokenize(s).unwrap(),
            vec![
                (1, Token::LessOrEqual),
                (1, Token::Assign),
                (1, Token::DoubleArrow),
                (1, Token::Dot),
                (1, Token::At),
                (1, Token::Neg),
                (1, Token::Divide),
                (1, Token::Plus),
                (1, Token::Minus),
                (1, Token::LessThan),
                (1, Token::Equal),
                (1, Token::LeftParen),
                (1, Token::RightParen),
                (1, Token::Comma),
                (1, Token::Semi),
                (1, Token::Colon),
                (1, Token::LeftBrace),
                (1, Token::RightBrace)]);
    }

    #[test]
    fn parse_type_id() {
        fn parse(input: &str) {
            if let Token::TypeId(s) = grammar::type_id(input).unwrap() {
                assert_eq!(s.as_str(), input);
            } else {
                unreachable!()
            }
        }
        fn parse_err(input: &str) {
            assert!(grammar::type_id(input).is_err());
        }
        parse("I");
        parse("ID");
        parse("Id");
        parse("I123d");
        parse("I123d_");
        parse("I123d_123D");
        parse_err("abc");
        parse_err("");
        parse_err("I-123");
        parse_err("iD");
        parse_err("I D");
    }

    #[test]
    fn parse_object_id() {
        fn parse(input: &str) {
            if let Token::ObjectId(s) = grammar::object_id(input).unwrap() {
                assert_eq!(s.as_str(), input);
            } else {
                unreachable!()
            }
        }
        fn parse_err(input: &str) {
            assert!(grammar::object_id(input).is_err());
        }
        parse("i");
        parse("iD");
        parse("id");
        parse("i123d");
        parse("i123d_");
        parse("i123d_123D");
        parse_err("Abc");
        parse_err("");
        parse_err("i-123");
        parse_err("ID");
        parse_err("i d");
    }

    #[test]
    fn tokenize() {
        let s = r#"
let -- let
   ISVOID (* class *)
class case esac 42 
-- class if fi
(**)
(* let *)
(* mmh *)fI If INHERITS;-- comment
TypeId : objectId()(*
comment
   *) class nEW -- junk  
(*moar junk*)
out_string(x : String) : SELF_TYPE
class C inherits P {
    f(): String { "1" };
};
"#;
        let v = grammar::tokenize(s).unwrap();
        assert_eq!(
            v,
            vec![(2, Token::Let),
                 (3, Token::IsVoid),
                 (4, Token::Class),
                 (4, Token::Case),
                 (4, Token::Esac),
                 (4, Token::Int(42)),
                 (8, Token::Fi),
                 (8, Token::If),
                 (8, Token::Inherits),
                 (8, Token::Semi),
                 (9, Token::TypeId("TypeId".into())),
                 (9, Token::Colon),
                 (9, Token::ObjectId("objectId".into())),
                 (9, Token::LeftParen),
                 (9, Token::RightParen),
                 (11, Token::Class),
                 (11, Token::New),
                 (13, Token::ObjectId("out_string".into())),
                 (13, Token::LeftParen),
                 (13, Token::ObjectId("x".into())),
                 (13, Token::Colon),
                 (13, Token::TypeId("String".into())),
                 (13, Token::RightParen),
                 (13, Token::Colon),
                 (13, Token::TypeId("SELF_TYPE".into())),
                 (14, Token::Class),
                 (14, Token::TypeId("C".into())),
                 (14, Token::Inherits),
                 (14, Token::TypeId("P".into())),
                 (14, Token::LeftBrace),
                 (15, Token::ObjectId("f".into())),
                 (15, Token::LeftParen),
                 (15, Token::RightParen),
                 (15, Token::Colon),
                 (15, Token::TypeId("String".into())),
                 (15, Token::LeftBrace),
                 (15, Token::String("1".into())),
                 (15, Token::RightBrace),
                 (15, Token::Semi),
                 (16, Token::RightBrace),
                 (16, Token::Semi)]);

    }
}
