import java.util.*;

public class Problem5_LibraryPlatform {
    static class Resource {
        String id, type;
        boolean reservable, downloadable;

        Resource(String id, String type, boolean reservable, boolean downloadable) {
            this.id = id;
            this.type = type;
            this.reservable = reservable;
            this.downloadable = downloadable;
        }
    }

    public static void main(String[] args) {
        List<Resource> resources = new ArrayList<>();

        add(resources, new Resource("B1", "Book", true, false));
        add(resources, new Resource("B1", "Book", true, false));
        add(resources, new Resource("E1", "EBook", true, true));

        reserve(resources, "B1", "M1");
        download(resources, "B1");
        download(resources, "E1");
        find(resources, "E1");
    }

    static void add(List<Resource> resources, Resource r) {
        int index = findIndex(resources, r.id);
        if (index >= 0) {
            System.out.println("duplicate rejected");
            return;
        }

        int insertAt = 0;
        while (insertAt < resources.size() &&
               resources.get(insertAt).id.compareTo(r.id) < 0) {
            insertAt++;
        }
        resources.add(insertAt, r);
        System.out.println(r.id + " added");
    }

    static int findIndex(List<Resource> resources, String id) {
        for (int i = 0; i < resources.size(); i++) {
            if (resources.get(i).id.equals(id)) return i;
        }
        return -1;
    }

    static void reserve(List<Resource> resources, String id, String member) {
        int i = findIndex(resources, id);
        if (i < 0) {
            System.out.println(id + " rejected: not found");
        } else if (!resources.get(i).reservable) {
            System.out.println(id + " rejected: reserve unsupported");
        } else {
            System.out.println(id + " reserved for " + member);
        }
    }

    static void download(List<Resource> resources, String id) {
        int i = findIndex(resources, id);
        if (i < 0) {
            System.out.println(id + " rejected: not found");
        } else if (!resources.get(i).downloadable) {
            System.out.println(id + " rejected: download unsupported");
        } else {
            System.out.println(id + " downloaded");
        }
    }

    static void find(List<Resource> resources, String id) {
        int i = findIndex(resources, id);
        if (i < 0) System.out.println(id + " not found");
        else System.out.println(id + " found at index " + i);
    }
}
