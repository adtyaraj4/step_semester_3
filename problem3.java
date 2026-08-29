class BusRoute {

    private String routeCode;
    private String routeName;
    private int priority;

    // Main constructor
    public BusRoute(String routeCode, String routeName, int priority) {

        this.routeCode = routeCode;
        this.routeName = routeName;
        this.priority = priority;
    }

    // Default priority constructor
    public BusRoute(String routeCode, String routeName) {
        this(routeCode, routeName, 5);
    }

    public int compareTo(BusRoute other) {

        // 1. Compare priority
        if (this.priority != other.priority) {
            return Integer.compare(this.priority, other.priority);
        }

        // 2. Compare route name ignoring case
        int nameCompare =
                this.routeName.compareToIgnoreCase(other.routeName);

        if (nameCompare != 0) {
            return nameCompare;
        }

        // 3. Compare route code ignoring case
        return this.routeCode.compareToIgnoreCase(other.routeCode);
    }

    public static BusRoute[] rankRoutes(BusRoute[] routes) {

        // Bubble sort
        for (int i = 0; i < routes.length - 1; i++) {

            for (int j = 0; j < routes.length - 1 - i; j++) {

                if (routes[j].compareTo(routes[j + 1]) > 0) {

                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }

    public String getRouteCode() {
        return routeCode;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        BusRoute[] ranked = rankRoutes(routes);

        for (BusRoute route : ranked) {
            System.out.println(route.getRouteCode());
        }
    }
}