package com.uom.icar.ui.quiz;

public class QuestionLibrary {

    private String mQuestions [] = {
            "When pedestrians are crossing the road near a pedestrian crossing, you should",
            "What is the legal age to ride a motorcycle without a gear?",
            "Which side of the road should a vehicle's driver drive on?",
            "If you are on a one way",
            "What does a red traffic signal signify?",
            "When should we use the fog lamps?",
            "What does a yellow light during traffic signify?",
            "Which vehicle is permitted to go at a speed of 60 kilometers per hour?",
            "What does over speeding?",
            "If a vehicle is parked on the side of the road late at night",
            "A person who drives a car in a public without a licence is liable for the following penalties:",
            "No honking is permitted in the vicinity of",
            "Where is parking of vehicle is prohibited?",
            "When should you halt your vehicle?",
            "What should you do when an ambulance is approaching?",
    };


    private String mChoices [][] = {
            {"Slow down, sound the horn, and then continue", "Sound the horn, and then continue", "Stop the vehicle and wait for pedestrians to cross the road before continuing"},
            {"20 years", "24 years", "16 years"},
            {"Left side", "Right side", "Middle"},
            {"Parking your vehicle is prohibited", "It is not advisable to drive in reverse gear", "Overtaking another vehicle is prohibited"},
            {"Vehicle should be slowed down", "Drive with caution", "Stop the vehicle completely"},
            {"During night", "When the vehicle in front of you is not using dim lights", "Mist is present"},
            {"Stop the vehicle completely", "Slow down the vehicle", "Reduce your vehicle's speed and drive cautiously"},
            {"Truck/heavy bus", "Cars", "None of the above"},
            {"An offence that may result in your driver's licence being revoked or suspended", "Is not an offence and be overlooked", "Is an offence, although no charges will be pressed"},
            {"The parking light should be left on", "It is necessary to lock the car", "None of the above"},
            {"A word of caution", "Seizure of the vehicle and/or penalties for the driver and owner", "Only penalty"},
            {"Hospitals, courts, and schools", "Police station", "Shopping places"},
            {"One-way road", "Middle of the road", "Footpath"},
            {"When involved in an accident or signaled by a traffic police", "While approaching a red traffic light", "None of the above"},
            {"Move to the side of the road to provide free passage", "Ignore the ambulance", "If there are no vehicles on other side, allow passage"}
    };



    private String mCorrectAnswers[] = {"Stop the vehicle and wait for pedestrians to cross the road before continuing", "16 years", "Left side", "It is not advisable to drive in reverse gear","Stop the vehicle completely", "Mist is present", "Reduce your vehicle's speed and drive cautiously","Cars", "An offence that may result in your driver's licence being revoked or suspended", "The parking light should be left on","Seizure of the vehicle and/or penalties for the driver and owner", "Hospitals, courts, and schools","Footpath", "When involved in an accident or signaled by a traffic police","Move to the side of the road to provide free passage"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice0 = mChoices[a][0];
        return choice0;
    }


    public String getChoice2(int a) {
        String choice1 = mChoices[a][1];
        return choice1;
    }

    public String getChoice3(int a) {
        String choice2 = mChoices[a][2];
        return choice2;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}

