"use strict";

var addWork;
var addEducation;
var addSkills;
var addLanguages;

window.onload = function () {
    addWork = document.getElementById("add-work");
    addEducation = document.getElementById("add-education");
    addSkills = document.getElementById("add-skills");
    addLanguages = document.getElementById("add-languages");
};


var switchVisibility = function (div) {
    div.hidden = !div.hidden;
};

var proficiencyIdToString = function (id) {
    switch (id) {
        case 1: return "Beginner";
        case 2: return "Conversational";
        case 3: return "Business";
        case 4: return "Fluent";
        case 5: return "Native";
        default : return "No proficiency";
    }
};