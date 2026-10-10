import java.util.*;

public class Problem2_CustomerRegistry {
    static class Customer {
        int id;
        String name;

        Customer(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (!(obj instanceof Customer)) return false;
            Customer other = (Customer) obj;
            return id == other.id && Objects.equals(name, other.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    public static void main(String[] args) {
        Set<Customer> customers = new HashSet<>();

        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(101, "Asha")));
        System.out.println(customers.add(new Customer(102, "Ravi")));
        System.out.println("unique count " + customers.size());
        System.out.println("contains: " + customers.contains(new Customer(101, "Asha")));
    }
}
