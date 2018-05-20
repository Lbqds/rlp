[![Build Status](https://travis-ci.org/Lbqds/rlp.svg?branch=master)](https://travis-ci.org/Lbqds/rlp)

Recursive Length Prefix (RLP) encoding implementation of [mantis](https://github.com/input-output-hk/mantis)

## usage

```scala
import rlp._
import rlp.RLPImplicits._
import rlp.RLPImplicitConversions._

case class Person(name: String, age: Int)

implicit val encDec = new RLPEncoder[Person] with RLPDecoder[Person] {
  override def encode(p: Person): RLPEncodeable = RLPList(p.name, p.age)

  override def decode(rlp: RLPEncodeable): Person = rlp match {
    case RLPList(name, age) => Person(name, age)
    case _ => throw new RuntimeException("invalid encode person")
  }
}

val lbqds = Person("lbqds", 23)
val rlp = encode(lbqds)
val decoded = decode[Person](rlp)

assert(lbqds == decoded)
```

serialize:

```scala
case class Person(name: String, age: Int) extends RLPSerializable {
  override def toRLPEncodable: RLPEncodeable = {
    RLPList(name, age)
  }
}
```

## diff

remove akka.util.ByteString and UInt256 from original implementation
