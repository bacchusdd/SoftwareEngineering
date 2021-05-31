package com.example.motiondetectionalert;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

// DTO model - PostResult
public class PromptResult {

    @SerializedName("prompt")
    private int prompt;

    @Override
    public String toString(){
        return "PromptResult{" +
                "Prompt = " + prompt;
    }

}
