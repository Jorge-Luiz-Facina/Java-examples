package org.example.sealed;

public sealed interface AbstractAnimal permits Dog {
}

non-sealed interface Dog extends AbstractAnimal {
}

//final interface Dog extends AbstractAnimal {
//}

interface DogX extends Dog {
}

//interface DogY implements AbstractAnimal {
//}


