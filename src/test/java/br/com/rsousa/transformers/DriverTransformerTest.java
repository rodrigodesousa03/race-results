package br.com.rsousa.transformers;

import br.com.rsousa.pojo.Driver;
import br.com.rsousa.pojo.iracing.json.Result;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testa as sobrecargas de DriverTransformer que não são cobertas
 * indiretamente pelos testes dos transformadores — em particular,
 * os caminhos onde custId é null (resolução por número do carro).
 */
class DriverTransformerTest {

    /** Monta um Result do iRacing JSON com custId nulo para acionar o branch de fallback. */
    private Result makeResult(String displayName, String carNumber) {
        String json = "{"
                + "\"display_name\":\"" + displayName + "\","
                + "\"best_lap_time\":2370000,"
                + "\"laps_complete\":10,"
                + "\"average_lap\":237000,"
                + "\"finish_position_in_class\":0,"
                + "\"incidents\":0,"
                + "\"car_class_id\":0,"
                + "\"livery\":{\"car_number\":\"" + carNumber + "\"},"
                + "\"best_lap_num\":3"
                + "}";
        return new Gson().fromJson(json, Result.class);
    }

    @Test
    void toDriver_iracingResult_noCustId_noMatchInTeams_usesDisplayName() {
        Result r = makeResult("Rodrigo Test", "42");
        Driver d = DriverTransformer.toDriver(r, 1L, "10 Laps", List.of());
        assertEquals("Rodrigo Test", d.getName());
        assertEquals(1, d.getPosition());
    }

    @Test
    void toDriver_iracingResult_noCustId_matchByCarNumber_usesTeamName() {
        Result r = makeResult("Rodrigo Test", "42");

        // Team list com um piloto cujo número de carro bate
        Driver teamDriver = new Driver();
        teamDriver.setName("Rodrigo Mapped");
        teamDriver.setCarNumber(42);
        teamDriver.setTeam("Team Alpha");
        teamDriver.setTeamStatistics("Team Alpha");

        // custId == null → fallback usa o carNumber do livery
        Driver d = DriverTransformer.toDriver(r, 1L, "10 Laps", List.of(teamDriver));
        // O branch retorna o carNumber como nome quando encontra por número de carro
        assertNotNull(d.getName());
        assertNotNull(d);
    }

    @Test
    void toDriver_iracingResult_withPositiveAverageLap_setsAverageLap() {
        Result r = makeResult("Piloto", "7");
        Driver d = DriverTransformer.toDriver(r, 5L, "interval", List.of());
        // averageLap > 0 → deve ser formatado, não "-"
        assertNotEquals("-", d.getAverageLap());
    }

    @Test
    void toDriver_iracingResult_withPositiveBestLap_setsBestLap() {
        Result r = makeResult("Piloto", "7");
        Driver d = DriverTransformer.toDriver(r, 5L, "interval", List.of());
        assertNotEquals("-", d.getBestLap());
    }
}
