import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class PayrollDispositionImpl implements PayrollDisposition {

    private Map<Employee, Double> payments;

    public PayrollDispositionImpl() {

        this.payments = new HashMap<>();
    }

    public double getTotal() {

        double total = 0;

        Collection<Double> paymentList = this.payments.values();

        for (Double salary : paymentList) {

            total += salary;
        }

        return total;
    }

    public double getAverage() {

        if (this.payments.isEmpty()) return 0;

        return getTotal() / this.payments.size();
    }

    public Map<Employee, Double> getPayments() {

        return this.payments;
    }

    @Override
    public void sendPayment(Employee empl, double payment) throws NullPointerException, IllegalArgumentException {

        if (empl == null) throw new NullPointerException("Employee to set Payment cant be NULL!");
        if (payment <= 0 && !(empl instanceof Volunteer)) throw new IllegalArgumentException("Payment to set cant be negative!");

        this.payments.put(empl, payment);
    }
}
