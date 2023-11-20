package br.com.rsousa.transformers;

import br.com.rsousa.pojo.ams2.Attributes;
import br.com.rsousa.pojo.ams2.Event;
import br.com.rsousa.pojo.ams2.Result;
import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Automobilista2StageDeserializer implements JsonDeserializer<Object> {
    @Override
    public br.com.rsousa.pojo.ams2.Stage deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        br.com.rsousa.pojo.ams2.Stage stage = new br.com.rsousa.pojo.ams2.Stage();

        stage.setEndTime(jsonObject.get("end_time").getAsInt());
        stage.setStartTime(jsonObject.get("start_time").getAsInt());

        if (jsonObject.has("results")) {
            JsonElement eventsElement = jsonObject.get("results");
            if (eventsElement.isJsonObject()) {
                stage.setResults(new ArrayList<Result>());
            } else if (eventsElement.isJsonArray()) {
                List<Result> resultList = getResults(eventsElement, context);

                stage.setResults(resultList);
            }
        }

        if (jsonObject.has("events")) {
            JsonElement eventsElement = jsonObject.get("events");
            if (eventsElement.isJsonObject()) {
                stage.setEvents(new ArrayList<br.com.rsousa.pojo.ams2.Event>());
            } else if (eventsElement.isJsonArray()) {
                List<br.com.rsousa.pojo.ams2.Event> eventList = getEvents(eventsElement, context);

                stage.setEvents(eventList);
            }
        }

        return stage;
    }

    private List<Result> getResults(JsonElement eventsElement, JsonDeserializationContext context) {
        JsonArray resultArray = eventsElement.getAsJsonArray();
        List<Result> resultList = new ArrayList<>();

        for (JsonElement resultElement : resultArray) {
            if (resultElement.isJsonObject()) {
                JsonObject resultObject = resultElement.getAsJsonObject();

                // Extract and process eventObject fields
                Result result = new Result();
                result.setAttributes(context.deserialize(resultObject.get("attributes"), Attributes.class));
                result.setIsPlayer(resultObject.get("is_player").getAsBoolean());
                result.setName(resultObject.get("name").getAsString());
                result.setParticipantid(resultObject.get("participantid").getAsInt());
                result.setRefid(resultObject.get("refid").getAsInt());
                result.setTime(resultObject.get("time").getAsInt());

                resultList.add(result);
            }
        }
        return resultList;
    }

    private List<Event> getEvents(JsonElement eventsElement, JsonDeserializationContext context) {
        JsonArray eventArray = eventsElement.getAsJsonArray();
        List<Event> eventList = new ArrayList<>();

        for (JsonElement eventElement : eventArray) {
            if (eventElement.isJsonObject()) {
                JsonObject eventObject = eventElement.getAsJsonObject();

                // Extract and process eventObject fields
                Event event = new Event();
                event.setAttributes(context.deserialize(eventObject.get("attributes"), Attributes.class));
                event.setEventName(eventObject.get("event_name").getAsString());
                event.setIsPlayer(eventObject.get("is_player").getAsBoolean());
                event.setName(eventObject.get("name").getAsString());
                event.setParticipantid(eventObject.get("participantid").getAsInt());
                event.setRefid(eventObject.get("refid").getAsInt());
                event.setTime(eventObject.get("time").getAsInt());

                eventList.add(event);
            }
        }
        return eventList;
    }
}