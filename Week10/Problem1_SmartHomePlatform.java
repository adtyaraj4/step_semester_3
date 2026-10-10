import java.util.*;

public class Problem1_SmartHomePlatform {
    static class Device {
        String id, type;
        boolean on;
        boolean dimmable, schedulable, energyMonitor;
        double energy;

        Device(String id, String type, boolean dimmable, boolean schedulable,
               boolean energyMonitor, double energy) {
            this.id = id;
            this.type = type;
            this.dimmable = dimmable;
            this.schedulable = schedulable;
            this.energyMonitor = energyMonitor;
            this.energy = energy;
        }
    }

    public static void main(String[] args) {
        Map<String, Device> devices = new HashMap<>();
        devices.put("L1", new Device("L1", "light", true, true, false, 0));
        devices.put("F1", new Device("F1", "fan", false, true, false, 0));
        devices.put("P1", new Device("P1", "plug", false, false, true, 12));

        String[] commands = {
            "ON L1", "DIM L1 40", "DIM F1 30",
            "SCHEDULE F1 22:00", "ENERGY P1", "ENERGY L1"
        };

        for (String command : commands) {
            String[] p = command.split("\\\\s+");
            Device d = devices.get(p[1]);
            if (d == null) {
                System.out.println(p[1] + " rejected: device not found");
                continue;
            }

            switch (p[0]) {
                case "ON":
                    d.on = true;
                    System.out.println(d.id + " is ON");
                    break;
                case "OFF":
                    d.on = false;
                    System.out.println(d.id + " is OFF");
                    break;
                case "DIM":
                    if (!d.dimmable) System.out.println(d.id + " rejected: DIM unsupported");
                    else System.out.println(d.id + " dimmed to " + p[2]);
                    break;
                case "SCHEDULE":
                    if (!d.schedulable) System.out.println(d.id + " rejected: SCHEDULE unsupported");
                    else System.out.println(d.id + " scheduled " + p[2]);
                    break;
                case "ENERGY":
                    if (!d.energyMonitor) System.out.println(d.id + " rejected: ENERGY unsupported");
                    else System.out.println(d.id + " energy " + d.energy + " kWh");
                    break;
                default:
                    System.out.println("Unknown command: " + p[0]);
            }
        }
    }
}
