import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class PayrollTest {
    private static class PayrollDBImpl implements PayrollDB {
        private List<Employee> db;

        public PayrollDBImpl(List<Employee> employees) {
            db = employees;
        }

        @Override
        public List<Employee> getEmployeeList() {
            return db;
        }
    }

    @Test
    public void testConstructorDisposition() {
        try {
            new Payroll(null, 1);
            fail("Payroll.Payroll() should throw a NullPointerException if the disposition argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testConstructorInvalidPayday() {
        try {
            new Payroll(new PayrollDispositionImpl(), -1);
            fail("Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException e) {
        }
        try {
            new Payroll(new PayrollDispositionImpl(), 0);
            fail("Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException e) {
        }
        try {
            new Payroll(new PayrollDispositionImpl(), 32);
            fail("Payroll.Payroll() should throw an IllegalArgumentException if the payday argument is invalid!");
        } catch (IllegalArgumentException e) {
        }
    }

    @Test
    public void testConstructorValidPayday() {
        try {
            for (int i = 1; i < 32; i++) {
                new Payroll(new PayrollDispositionImpl(), i);
            }
        } catch (IllegalArgumentException e) {
            fail("Payroll.Payroll() should not throw an IllegalArgumentException if the payday argument is valid!");
        }
    }

    @Test
    public void testDoPayrollNullArgument() {
        try {
            new Payroll(new PayrollDispositionImpl(), 1).doPayroll(null);
            fail("Payroll.doPayroll() should throw a NullPointerException if the argument is null!");
        } catch (NullPointerException e) {
        }
    }

    @Test
    public void testDoPayroll() {
        PayrollDispositionImpl disposition = new PayrollDispositionImpl();
        List<Employee> employeeList;
        Map<Employee, Double> payments;

        Employee e1 = new Appointee("a1", 21, 205, 13);
        Employee e2 = new Volunteer("v1");
        Employee e3 = new Appointee("a2", 15, 120, 25);
        Employee e4 = new Appointee("a3", 21, 163, 11);
        Employee e5 = new Volunteer("v2");
        Employee e6 = new Appointee("a4", 18, 200, 10);
        Employee e7 = new Appointee("a5", 21, 200, 10);
        employeeList = Arrays.asList(e1, e2, e3, e4, e5, e6, e7);

        new Payroll(disposition, 21).doPayroll(new PayrollDBImpl(employeeList));
        payments = disposition.getPayments();

        assertTrue("Payroll.doPayroll() should send a payment for every Employee to be paid!",
                payments.get(e1) == 1599);
        assertTrue("Payroll.doPayroll() should send a payment for every Employee to be paid!",
                payments.get(e4) == 1075.8);
        assertTrue("Payroll.doPayroll() should send a payment for every Employee to be paid!",
                payments.get(e7) == 1200);
        assertTrue("Payroll.doPayroll() should only send payments for Employees to be paid!", payments.size() == 3);
    }
}
