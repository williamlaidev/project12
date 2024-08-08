package framework.data;

import entity.operation_result.OperationResultSuccessFactory;
import entity.operation_result.OperationResultFailureFactory;
import entity.restaurant.Restaurant;
import entity.location.Location;
import entity.DishType;
import entity.operation_result.OperationResult;
import interface_adapter.data.SQLiteRestaurantDataAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SQLiteRestaurantRepositoryTest {

    @Mock
    private DatabaseConfig databaseConfig;

    @Mock
    private SQLiteRestaurantDataAdapter dataAdapter;

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private SQLiteRestaurantRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        repository = new SQLiteRestaurantRepository(databaseConfig, dataAdapter, new OperationResultSuccessFactory(), new OperationResultFailureFactory());
    }


    @Test
    public void testFindById() throws SQLException {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(10.0, 20.0), "123 Test St", DishType.PIZZA, 4.5, "test.jpg");

        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(dataAdapter.adaptToRestaurant(resultSet)).thenReturn(restaurant);

        Optional<Restaurant> result = repository.findById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeQuery();

        assertTrue(result.isPresent());
        assertEquals(restaurant, result.get());
    }

    @Test
    public void testFindAll() throws SQLException {
        Restaurant restaurant = new Restaurant("1", "Test Restaurant", new Location(10.0, 20.0), "123 Test St", DishType.PIZZA, 4.5, "test.jpg");

        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery(anyString())).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(dataAdapter.adaptToRestaurant(resultSet)).thenReturn(restaurant);

        List<Restaurant> result = repository.findAll();

        verify(preparedStatement, times(1)).executeQuery(anyString());

        assertEquals(1, result.size());
        assertEquals(restaurant, result.get(0));
    }

    @Test
    public void testDeleteById() throws SQLException {
        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        boolean result = repository.deleteById("1");

        verify(preparedStatement, times(1)).setString(1, "1");
        verify(preparedStatement, times(1)).executeUpdate();

        assertTrue(result);
    }

    @Test
    public void testClearAll() throws SQLException {
        when(databaseConfig.connect()).thenReturn(connection);
        when(connection.createStatement()).thenReturn(preparedStatement);
        when(preparedStatement.executeUpdate(anyString())).thenReturn(1);

        boolean result = repository.clearAll();

        verify(preparedStatement, times(1)).executeUpdate(anyString());

        assertTrue(result);
    }
}
