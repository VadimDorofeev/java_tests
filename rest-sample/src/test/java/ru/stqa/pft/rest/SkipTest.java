package ru.stqa.pft.rest;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;
import org.testng.annotations.Test;

import java.io.IOException;

public class SkipTest {

    @Test
    public void skipIfIssueIsNotOpen() throws IOException {
        skipIfNotFixed(2306);
    }

    public void skipIfNotFixed(int issueId) throws IOException{
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    private boolean isIssueOpen(int issueId) throws IOException {
        Issue issue = getIssueById(issueId);
        if (issue.getStatus().equals("Open")) {
            return true;
        } else {
            return false;
        }
    }

    private Issue getIssueById(int issueId) throws IOException {
        String json = getExecutor().execute(Request.Get("https://bugify.stqa.ru/api/issues/" + issueId + ".json"))
                .returnContent().asString();
        JsonElement parsed = new JsonParser().parse(json);
        JsonElement issues = parsed.getAsJsonObject().get("issues").getAsJsonArray().get(0);
        return new Gson().fromJson(issues, new TypeToken<Issue>(){}.getType());
    }

    private Executor getExecutor(){
        return Executor.newInstance()
                .auth("288f44776e7bec4bf44fdfeb1e646490","");
    }
}
