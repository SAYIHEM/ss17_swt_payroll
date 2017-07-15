public abstract class Employee implements Comparable<Employee> {

    private String id;

    public Employee(String id) throws NullPointerException, IllegalArgumentException {

        if (id == null) throw new NullPointerException("ID to set cant be NULL!");
        if (id.isEmpty()) throw new IllegalArgumentException("ID to set cant be empty!");

        this.id = id;
    }

    public String getId() {

        return this.id;
    }

    public abstract boolean isPayday(int dayOfMonth);
    public abstract double calculatePay() throws UnpayableEmployeeException;
    public abstract double calculateDeductions() throws UnpayableEmployeeException;

    public int compareTo(Employee employee) {

        if (this.id.equals(employee.getId())) {

            return 0;

        } else {

            return -1;
        }
    }
}
