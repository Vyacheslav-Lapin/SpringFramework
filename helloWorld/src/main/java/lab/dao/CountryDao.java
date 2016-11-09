package lab.dao;

import lab.model.Country;
import lombok.val;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import java.util.List;

public class CountryDao extends JdbcDaoSupport {
    private static final String LOAD_COUNTRIES_SQL = "insert into country (name, code_name) values ";

    private static final String GET_ALL_COUNTRIES_SQL = "select * from country";
    private static final String GET_COUNTRIES_BY_NAME_SQL = "select * from country where name like :name";
    private static final String GET_COUNTRY_BY_NAME_SQL = "select * from country where name = '";
    private static final String GET_COUNTRY_BY_CODE_NAME_SQL = "select * from country where code_name = '";

    private static final String UPDATE_COUNTRY_NAME_SQL_1 = "update country SET name = '";
    private static final String UPDATE_COUNTRY_NAME_SQL_2 = "' where code_name='";

    public static final String[][] COUNTRY_INIT_DATA = {{"Australia", "AU"},
            {"Canada", "CA"}, {"France", "FR"}, {"Hong Kong", "HK"},
            {"Iceland", "IC"}, {"Japan", "JP"}, {"Nepal", "NP"},
            {"Russian Federation", "RU"}, {"Sweden", "SE"},
            {"Switzerland", "CH"}, {"United Kingdom", "GB"},
            {"United States", "US"}};

    private static final RowMapper<Country> COUNTRY_ROW_MAPPER = (rs, rowNum) ->
            new Country()
                    .setId(rs.getInt("id"))
                    .setName(rs.getString("name"))
                    .setCodeName(rs.getString("code_name"));

    public List<Country> getCountryList() {
        return getJdbcTemplate()
                .query(GET_ALL_COUNTRIES_SQL, COUNTRY_ROW_MAPPER);
    }

    public List<Country> getCountryListStartWith(String name) {
        val sqlParameterSource = new MapSqlParameterSource("name", name + "%");
        return getNamedParameterJdbcTemplate()
                .query(GET_COUNTRIES_BY_NAME_SQL, sqlParameterSource, COUNTRY_ROW_MAPPER);
    }

    public void updateCountryName(String codeName, String newCountryName) {
        getJdbcTemplate().execute(
                UPDATE_COUNTRY_NAME_SQL_1 + newCountryName
                        + UPDATE_COUNTRY_NAME_SQL_2 + codeName + "'"
        );
    }

    public void loadCountries() {
        for (String[] countryData : COUNTRY_INIT_DATA)
            getJdbcTemplate().execute(LOAD_COUNTRIES_SQL + "('" + countryData[0] +
                    "', '" + countryData[1] + "');");
    }

    public Country getCountryByCodeName(String codeName) {
        String sql = GET_COUNTRY_BY_CODE_NAME_SQL + codeName + "'";
        return getJdbcTemplate().query(sql, COUNTRY_ROW_MAPPER).get(0);
    }

    public Country getCountryByName(String name) throws CountryNotFoundException {
        List<Country> countryList = getJdbcTemplate()
                .query(GET_COUNTRY_BY_NAME_SQL + name + "'", COUNTRY_ROW_MAPPER);
        if (countryList.isEmpty())
            throw new CountryNotFoundException();
        return countryList.get(0);
    }

    private NamedParameterJdbcTemplate getNamedParameterJdbcTemplate() {
        return new NamedParameterJdbcTemplate(getDataSource());
    }
}
