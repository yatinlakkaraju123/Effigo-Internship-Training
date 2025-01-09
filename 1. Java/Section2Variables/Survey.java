package Section2Variables;public class Survey {
    public static void main(String[] args) {

        //*********PART A: PICKING UP THE USER'S ANSWERS*********
        System.out.println("Welcome. Thank you for taking the survey");

        System.out.println("What is your name?");
        String name;

        System.out.println("How much money do you spend on coffee?");
        double coffeePrice;

        System.out.println("How much money do you spend on fast food?");
        double foodPrice;


        System.out.println("How many times a week do you buy coffee?");
        int coffeeAmount;

        System.out.println("How many times a week do you buy fast food?");
        int foodAmount;

        //*********PART B: RESPONDING TO THE USER**********
        System.out.println("Thank you <name> for answering all <counter> questions");
        System.out.println("Weekly, you spend $<totalPrice> on coffee");
        System.out.println("Weekly, you spend $<totalPrice> on food");

    }
}
