public class Appointee extends Employee {

    private int dayOfMonth;
    private int hoursPerMonth;
    private double payPerHour;

    public Appointee(String id, int dayOfMonth, int hoursPerMonth, double payPerHour) throws NullPointerException, IllegalArgumentException {
        super(id);

        if (dayOfMonth < 1 || dayOfMonth > 31) throw new IllegalArgumentException("DayOfMonth has to be a value between 1 - 31!");
        if (hoursPerMonth < 1) throw new IllegalArgumentException("Working hours per Month cant be negative!");
        if (payPerHour <= 0) throw new IllegalArgumentException("PayPerHour cant be negative!");

        this.dayOfMonth = dayOfMonth;
        this.hoursPerMonth = hoursPerMonth;
        this.payPerHour = payPerHour;
    }

    @Override
    public boolean isPayday(int dayOfMonth) {

        return this.dayOfMonth == dayOfMonth;
    }

    @Override
    public double calculatePay() throws UnpayableEmployeeException {

        return this.payPerHour * this.hoursPerMonth;
    }

    @Override
    public double calculateDeductions() {

        return this.payPerHour * this.hoursPerMonth * 0.4;
    }
}
