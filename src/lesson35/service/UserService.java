package lesson35.service;

import lesson35.controller.HotelController;
import lesson35.controller.RoomController;
import lesson35.exception.BadRequestException;
import lesson35.exception.BadInputException;
import lesson35.model.Hotel;
import lesson35.model.Room;
import lesson35.model.User;
import lesson35.model.UserType;
import lesson35.repository.UserRepository;

import java.io.IOException;
import java.util.ArrayList;

public class UserService {


    private UserRepository userRepository = new UserRepository();
    private HotelController hotelController = new HotelController();
    private RoomController roomController = new RoomController();

    ArrayList<User> usersArrayList = userRepository.buildArrayListOfUsers();
    private User registredUser;

    public User registerUser(User user) throws BadInputException, BadRequestException {

        validateBeforeRegisterUser(user);
        checkIfUserIsExist(user);
        return userRepository.registerUser(user);
    }

    private void checkIfUserIsExist(User user) throws BadRequestException {
        for (User usr : usersArrayList) {
            if (user.equals(usr)) {
                throw new BadRequestException("User with name: " + user.getUserName() + " is present in base");
            }
        }
    }

    private void validateBeforeRegisterUser(User user) throws BadInputException {
        if (user.getUserName() == null || user.getPassword() == null ||
                user.getCountry() == null || user.getUserType() == null) {
            throw new BadInputException("All fields should be filled. User with id = " + user.getId());
        }
    }

    public User login(String userName, String pass) throws BadRequestException, BadInputException {
        User u = userRepository.getUserByName(userName);
        if (u.getUserName().equals(userName) && u.getPassword().equals(pass)) {
            registredUser = u;
            System.out.println("user " + userName + " logined");
            return u;
        }
        throw new BadInputException("User " + userName + " is not logined by reason - wrong  userName or password");
    }

    public User logout(String userName) throws BadInputException, BadRequestException {
        User u = userRepository.getUserByName(userName);
        if (registredUser.equals(u)) {
            registredUser = null;
            System.out.println("user " + u.getUserName() + " loguot.");
            return u;
        }
        throw new BadInputException("User " + u.getUserName() + " is not logout");
    }

    public Hotel addHotel(Hotel hotel) throws BadRequestException, IOException, BadInputException {
        if (registredUser == null) {
            throw new BadRequestException("User is not logined. Hotel is not added");
        }

        if (registredUser.getUserType().equals(UserType.ADMIN)) {
            return hotelController.addHotel(hotel);
        }
        throw new BadRequestException("Current user " + registredUser.getUserName() + " don`t have permission to add hotel");
    }

    public Long deleteHotel(long hotelId) throws BadRequestException, IOException {
        if (registredUser == null) {
            throw new BadRequestException("User is not logined. Hotel is not added");
        }
        if (registredUser.getUserType().equals(UserType.ADMIN)) {
            return hotelController.deleteHotel(hotelId);
        }
        throw new BadRequestException("Current user " + registredUser.getUserName() + " don`t have permission to delete hotel");
    }

    public Room addRoom(Room room) throws BadRequestException, BadInputException {
        if (registredUser == null) {
            throw new BadRequestException("User is not logined. Room is not added");
        }
        if (registredUser.getUserType().equals(UserType.ADMIN)) {
            return roomController.addRoom(room);
        }
        throw new BadRequestException("Current user " + registredUser.getUserName() + " don`t have permission to add room");
    }

    public long deleteRoom(long roomId) throws BadRequestException {
        if (registredUser == null) {
            throw new BadRequestException("User is not logined. Room is not deletet");
        }
        if (registredUser.getUserType().equals(UserType.ADMIN)) {
            return roomController.deleteRoom(roomId);
        }
        throw new BadRequestException("Current user " + registredUser.getUserName() + " don`t have permission to delete room");

    }

    public User gerUserById(long userId) throws BadRequestException {
        return userRepository.getUserById(userId);
    }
}
