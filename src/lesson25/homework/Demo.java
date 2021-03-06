package lesson25.homework;

import lesson25.homework.exception.BadRequestException;
import lesson25.homework.exception.InternalServerException;

public class Demo {
    public static void main(String[] args) {
        GeneralDAO<Book> daoBook = new GeneralDAO<>();
        try {
            daoBook.save(new Book());
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (InternalServerException e) {
            e.printStackTrace();
        }

        try {
            daoBook.save(new Book());
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (InternalServerException e) {
            e.printStackTrace();
        }

        GeneralDAO<Car> daoCar = new GeneralDAO<>();
        try {
            daoCar.save(new Car());
        } catch (BadRequestException e) {
            e.printStackTrace();
        } catch (InternalServerException e) {
            e.printStackTrace();
        }

    }
}
