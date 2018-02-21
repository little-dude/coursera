use std::fmt;

#[derive(Debug, Clone, PartialEq, Hash)]
pub enum Token {
    Inherits,
    Semi,
    Not,
    Let,
    In,
    Class,
    /// `loop` keyword
    Loop,
    /// `pool` keyword
    Pool,
    /// `<-` operator
    Assign,
    /// `if` keyword
    If,
    /// `then` keyword
    Then,
    /// `else` keyword
    Else,
    /// `fi` keyword
    Fi,
    /// `of` keyword
    Of,
    /// represent the end of the input stream
    Eof,
    /// `new` keyword
    New,
    /// `isvoid` keyword
    IsVoid,
    Error,
    /// `=>` operator
    DoubleArrow,
    While,
    Case,
    Esac,
    ObjectId(String),
    TypeId(String),
    Bool(bool),
    Int(u32),
    /// Represent a string. For example:
    /// ```no_rust
    /// "this is a string"
    /// "this is a \
    /// string"
    /// "this\nis\ta string"
    /// ```
    String(String),
    /// `(`
    LeftParen,
    /// `)`
    RightParen,
    /// `{`
    LeftBrace,
    /// `}`
    RightBrace,
    /// `=`
    Equal,
    /// `@`
    At,
    /// `/`
    Divide,
    /// `+`
    Plus,
    /// `.`
    Dot,
    /// `:`
    Colon,
    /// `~`
    Neg,
    /// `*`
    Multiply,
    /// `-`
    Minus,
    /// `<`
    LessThan,
    /// `<=`
    LessOrEqual,
    /// `,`
    Comma,
    Err(String),
}

impl fmt::Display for Token {
    fn fmt(&self, f: &mut fmt::Formatter) -> fmt::Result {
        match *self {
            Token::Inherits             => write!(f, "INHERITS"),
            Token::Semi                 => write!(f, "SEMI"),
            Token::Not                  => write!(f, "NOT"),
            Token::Let                  => write!(f, "LET"),
            Token::In                   => write!(f, "IN"),
            Token::Class                => write!(f, "CLASS"),
            Token::Loop                 => write!(f, "LOOP"),
            Token::Pool                 => write!(f, "POOL"),
            Token::Assign               => write!(f, "ASSIGN"),
            Token::If                   => write!(f, "IF"),
            Token::Then                 => write!(f, "THEN"),
            Token::Else                 => write!(f, "ELSE"),
            Token::Fi                   => write!(f, "FI"),
            Token::Of                   => write!(f, "OF"),
            Token::Eof                  => write!(f, "EOF"),
            Token::New                  => write!(f, "NEW"),
            Token::IsVoid               => write!(f, "ISVOID"),
            Token::Error                => write!(f, "ERROR"),
            Token::DoubleArrow          => write!(f, "DARROW"),
            Token::While                => write!(f, "WHILE"),
            Token::Case                 => write!(f, "CASE"),
            Token::Esac                 => write!(f, "ESAC"),
            Token::ObjectId(ref value)  => write!(f, "OBJECTID = {}", value),
            Token::TypeId(ref value)    => write!(f, "TYPEID = {}", value),
            Token::Bool(ref value)      => write!(f, "BOOL_CONST = {}", value),
            Token::Int(ref value)       => write!(f, "INT_CONST = {}", value),
            Token::String(ref value)    => write!(f, "STR_CONST = \"{}\"", value), // FIXME: print escaped string
            Token::LessOrEqual          => write!(f, "LE"),
            Token::LeftParen            => write!(f, "("),
            Token::RightParen           => write!(f, ")"),
            Token::LeftBrace            => write!(f, "{{"),
            Token::RightBrace           => write!(f, "}}"),
            Token::Equal                => write!(f, "="),
            Token::At                   => write!(f, "@"),
            Token::Divide               => write!(f, "/"),
            Token::Plus                 => write!(f, "+"),
            Token::Dot                  => write!(f, "."),
            Token::Colon                => write!(f, ":"),
            Token::Neg                  => write!(f, "~"),
            Token::Multiply             => write!(f, "*"),
            Token::Minus                => write!(f, "-"),
            Token::LessThan             => write!(f, "<"),
            Token::Comma                => write!(f, ","),
            Token::Err(ref value)       => write!(f, "ERROR = {}", value),
        }
    }
}
