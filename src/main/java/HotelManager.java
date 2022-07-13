import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import logger.Logger;

public class HotelManager {
    private final static int NUMBER_OF_FLOORS = 1;
    private final static int ROOMS_PER_FLOOR = 10;
    private final static Logger logger = Logger.getInstance();

    public static void main(String[] args) {
        Logger.getInstance().log("Managing hotel...");

        // create hotel rooms
        List<HotelRoomInterface> hotelRooms = IntStream
                .range(0, NUMBER_OF_FLOORS * ROOMS_PER_FLOOR)
                .mapToObj(i -> new HotelRoom()).collect(Collectors.toList());

        // create hotel floors
        List<HotelFloor> hotelFloors = IntStream
                .range(0, NUMBER_OF_FLOORS)
                .mapToObj(i -> new HotelFloor()).collect(Collectors.toList());

        // add hotel rooms to hotel floors
        for (int i = 0; i < (NUMBER_OF_FLOORS * ROOMS_PER_FLOOR); i++) {
            hotelFloors.get(i % NUMBER_OF_FLOORS).addHotelRoom(hotelRooms.get(i));
        }

        /*
         * take actions on rooms and floors and examine your output to ensure you
         * implemented the desired behaviors
         */

        // Clean an entire floor
        logger.log("---- Cleaning an entire floor ----");
        hotelFloors.forEach(HotelFloor::clean);
        System.out.println();
        // Book an entire foor
        logger.log("---- Booking an entire floor ----");
        hotelFloors.forEach(floor -> floor.book("Howard"));
        System.out.println();

        logger.log("---- Cleaning a room ----");
        hotelRooms.get(0).clean();
        System.out.println();

        logger.log("---- Booking a room ----");
        hotelRooms.get(0).book("John");
        System.out.println();

    }
}

interface HotelRoomInterface {
    void book(String guestName);

    void clean();
}

class HotelRoom implements HotelRoomInterface {
    public void book(String guestName) {
        Logger.getInstance().log("Booked a room for " + guestName);
    }

    public void clean() {
        Logger.getInstance().log("Cleaned room");
    }
}

class HotelFloor implements HotelRoomInterface {
    private List<HotelRoomInterface> hotelRooms = new ArrayList<HotelRoomInterface>();

    public void book(String guestName) {
        hotelRooms.forEach(child -> {
            child.book(guestName);
        });
    }

    public void clean() {
        hotelRooms.forEach(child -> child.clean());
    }

    public void addHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.add(hotelRoom);
    }

    public void removeHotelRoom(HotelRoomInterface hotelRoom) {
        hotelRooms.remove(hotelRoom);
    }
}
