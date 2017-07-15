import java.util.List;

public class Payroll {

    private PayrollDisposition disposition;
    private int payday;

    public Payroll(PayrollDisposition disposition, int payday) throws NullPointerException, IllegalArgumentException {

        if (disposition == null) throw new NullPointerException("Disposition to set cant be NULL!");
        if (payday < 1 || payday > 31) throw new IllegalArgumentException("Payday has to be a value between 1 - 31!");

        this.disposition = disposition;
        this.payday = payday;
    }

    public void doPayroll(PayrollDB db) throws NullPointerException {

        if (db == null) throw new NullPointerException("DB to do Payroll cant be NULL!");

        List<Employee> employeeList = db.getEmployeeList();

        for (Employee empl : employeeList) {

            try {

                if (empl.isPayday(payday)) {

                    this.disposition.sendPayment(empl, empl.calculatePay() - empl.calculateDeductions());
                }

            } catch (UnpayableEmployeeException e) {}
        }
    }

}
