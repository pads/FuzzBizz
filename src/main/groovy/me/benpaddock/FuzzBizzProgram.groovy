package me.benpaddock

/**
 * User: pads
 * Date: 07/03/12 
 */
class FuzzBizzProgram {

    def static final DEFAULT_NUMBER_RANGE = 100;
    def static final FUZZ = 'Fuzz';
    def static final BIZZ = 'Bizz';

    def FuzzBizzProgram() {

        Integer.metaClass.methodMissing = { String methodName, methodArguments ->
            if(methodName.startsWith('isAMultipleOf')) {
                try {
                    return delegate.mod(methodName[-1].toInteger()) == 0
                } catch (NumberFormatException numberFormatException) {
                    throw new MissingMethodException(methodName, Integer.class, methodArguments)
                }
            } else {
                throw new MissingMethodException(methodName, Integer.class, methodArguments)
            }
        }

    }

    def run(numberOf) {

        def numbers = []
        numberOf.times { index ->
            def number = index + 1
            if(number.isAMultipleOf3() && number.isAMultipleOf5()) {
                numbers.add(FUZZ + BIZZ)
            } else if(number.isAMultipleOf3()) {
                numbers.add(FUZZ)
            } else if(number.isAMultipleOf5()) {
                numbers.add(BIZZ)
            } else {    
                numbers.add(number)
            }
        }
        println numbers
        return numbers
    }

    public static void main(String[] arguments) {
        new FuzzBizzProgram().run(DEFAULT_NUMBER_RANGE);
    }

}


