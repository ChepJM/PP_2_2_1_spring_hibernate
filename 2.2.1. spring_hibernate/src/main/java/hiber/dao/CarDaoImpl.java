package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarDaoImpl implements CarDao{
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        String query = "FROM Car";
        List<Car> listCars = sessionFactory.getCurrentSession().createQuery(query).getResultList();
        return listCars;
    }

    @Override
    public User getUserByCarModelAndSeries(String model, int series) {
        String query = "FROM Car WHERE model = :model AND series = :series";
        Car car = (Car) sessionFactory.getCurrentSession().createQuery(query)
                .setParameter("model", model)
                .setParameter("series", series).getSingleResult();
        return car.getCarUser();
    }
}
