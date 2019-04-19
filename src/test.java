public class test {
    public static void main(String[] args) {
        System.out.println(AccountService.passwordIsValid("Qwerty2!"));
        System.out.println(AccountService.emailIsValid("teotsi@gmail.com"));
        AccountService.register("","",(float) 0.0,"teotsi@gmail.com","Qwerty2!");
        System.out.println(AccountService.login("teotsi@gmail.com","Qwerty2!"));
        AccountService.save();

    }
}
