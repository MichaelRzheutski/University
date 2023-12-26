package com.solvd.university.persistence.impl;

import com.solvd.university.domain.Building;
import com.solvd.university.persistence.BuildingRepositoryDao;
import com.solvd.university.persistence.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static com.solvd.university.util.ConsoleColors.*;
import static com.solvd.university.util.MyLogger.MY_LOGGER;

public class BuildingRepositoryImpl implements BuildingRepositoryDao {
    private static final ConnectionPool CONNECTION_POOL = ConnectionPool.getInstance();
    private static final String INSERT_INTO_BUILDINGS =
            "INSERT INTO buildings(building_name, classroom_id) values(?, ?);";
    private static final String SELECT_ALL_FROM_BUILDINGS =
            "SELECT * FROM university.buildings;";
    private static final String FIND_BUILDING_BY_ID = "SELECT * FROM buildings WHERE building_id = ?;";
    private static final String UPDATE_BUILDING_NAME = "UPDATE buildings SET building_name = ? WHERE building_id = ?;";
    private static final String DELETE_FROM_BUILDINGS = "DELETE FROM buildings WHERE building_id = ?;";
    private static final String COUNT_ENTRIES = "SELECT COUNT(*) AS buildings_count FROM buildings;";

    @Override
    public void create(Building building) {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_INTO_BUILDINGS, Statement.RETURN_GENERATED_KEYS)) {

            String enteredBuildingName;
            MY_LOGGER.info(ANSI_GREEN + "Введите название корпуса" + ANSI_RESET);

            if (scanner.hasNext()) {
                enteredBuildingName = scanner.nextLine();

                preparedStatement.setString(1, enteredBuildingName);
                preparedStatement.setInt(2, 1);
                preparedStatement.executeUpdate();

                MY_LOGGER.info(ANSI_GREEN + "Был добавлен корпус: " + ANSI_YELLOW
                        + enteredBuildingName + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Невозможно создать корпус!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void findById() {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);
        int enteredBuldingId;

        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_BUILDING_BY_ID)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID корпуса" + ANSI_RESET);

            if (scanner.hasNextInt()) {
                enteredBuldingId = scanner.nextInt();
                preparedStatement.setLong(1, enteredBuldingId);
                ResultSet resultSet = preparedStatement.executeQuery();
                resultSet.next();

                MY_LOGGER.info(ANSI_GREEN + "Найден корпус: "
                        + ANSI_YELLOW + resultSet.getString(2) + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не у далось найти корпус по ID!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public List<Building> findAll() {
        List<Building> buildings;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_FROM_BUILDINGS)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            buildings = mapBuildings(resultSet);

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось найти все корпуса!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return buildings;
    }

    @Override
    public void update(Building building) {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BUILDING_NAME)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID корпуса" + ANSI_RESET);
            int enteredBuildingId;
            String buildingNewName;

            if (scanner.hasNextInt()) {
                enteredBuildingId = scanner.nextInt();

                MY_LOGGER.info(ANSI_GREEN + "Введите название корпуса:" + ANSI_RESET);
                if (scanner.hasNext()) {
                    buildingNewName = scanner.next();

                    preparedStatement.setString(1, buildingNewName);
                    preparedStatement.setLong(2, enteredBuildingId);
                    preparedStatement.executeUpdate();
                }

                MY_LOGGER.info(ANSI_GREEN + "Обновлён корпус с ID: " + ANSI_YELLOW
                        + enteredBuildingId + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось обновить название корпуса!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public void deleteById() {
        Connection connection = CONNECTION_POOL.getConnection();
        Scanner scanner = new Scanner(System.in);

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_FROM_BUILDINGS)) {
            MY_LOGGER.info(ANSI_GREEN + "Введите ID корпуса:" + ANSI_RESET);
            int enteredBuildingId;

            if (scanner.hasNextInt()) {
                enteredBuildingId = scanner.nextInt();

                preparedStatement.setLong(1, enteredBuildingId);
                preparedStatement.executeUpdate();

                MY_LOGGER.info(ANSI_GREEN + "Удалён корпус с ID: " + ANSI_YELLOW
                        + enteredBuildingId + ANSI_RESET + "\n");
            } else {
                MY_LOGGER.info(ANSI_RED + "Неверная операция, попробуйте ещё раз!" + ANSI_RESET);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось удалить корпус!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
    }

    @Override
    public Long countOfEntries() {
        Long entriesCounter;
        Connection connection = CONNECTION_POOL.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_ENTRIES)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            entriesCounter = resultSet.getLong(1);
            MY_LOGGER.info(ANSI_GREEN + "Общее количество корпусов: " + ANSI_YELLOW
                    + entriesCounter + ANSI_RESET + "\n");

        } catch (SQLException e) {
            throw new RuntimeException("Не удалось посчитать корпуса!", e);
        } finally {
            CONNECTION_POOL.releaseConnection(connection);
        }
        return entriesCounter;
    }

    private static List<Building> mapBuildings(ResultSet resultSet) {
        List<Building> buildings = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Building building = new Building();
                building.setBuildingId(resultSet.getLong(1));
                building.setBuildingName(resultSet.getString(2));
                building.setClassroomId(resultSet.getLong(3));
                buildings.add(building);

                MY_LOGGER.info(
                        resultSet.getString(1) + " | " +
                                resultSet.getString(2) + " | "
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Не удалось добавить корпус!", e);
        }
        return buildings;
    }
}
