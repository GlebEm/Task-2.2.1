package hiber.model;
//Создайте сущность Car с полями String model и int series,
// на которую будет ссылаться User с помощью связи one-to-one.

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "cars")

//public class Car implements Serializable { //если что-то не числовое для первичного ключа
    // в хибернейтей, то в классе сущности реализуем сериалазайзабл
public class Car {
    //первая сторона отношения
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "model")
    private String model;

    @Column(name = "series")
    private int series;

    @OneToOne(mappedBy = "car")
   // @JoinColumn(name = "user.id", referencedColumnName = "id")
    private User user;

    public Car(){}

    public Car(String model, int series) {
        this.model = model;
        this.series = series;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSeries() {
        return series;
    }

    public void setSeries(int series) {
        this.series = series;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        if (model != null ? !model.equals(car.model) : car.model != null) return false;
        return series != 0 ? series == (car.series) : car.series == 0;
    }

    @Override
    public int hashCode() {
        int result = model != null ? model.hashCode() : 0;
        result = 31 * result + Integer.hashCode(series);
        return result;
    }

    @Override
    public String toString() {
        return "car :[" +
//                "user=" + user +
                "model: '" + model +
                "', series: " + series +
                ']';
    }
}
