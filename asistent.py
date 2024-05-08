import speech_recognition as sr
import pyttsx3
from datetime import date
import random
import webbrowser

def say(command):
    engine = pyttsx3.init()
    VoiceRate = 130
    engine.setProperty('rate', VoiceRate)
    engine.say(command)
    engine.runAndWait()

def record_text(text = ""):
    while True:
        try:
            with sr.Microphone() as source2:
                r = sr.Recognizer()
                r.adjust_for_ambient_noise(source2, duration=0.2)

                say(text)
                print("Listening")

                audio2 = r.listen(source2)

                text = r.recognize_google(audio2)

                print(text)

                return text

        except sr.RequestError as e:
            print("Could not request results; {0}".format(e))
            return "Failed"

        except sr.UnknownValueError:
            print("Unknown error occured.")
            return "Failed"

def main():
    while True:
        text = record_text()
        if text == "hey Jarvis":
            looped = False
            say("Is there anything I can help you with?")
            while text == "Failed" or text == "hey Jarvis":
                text = record_text()
                if looped:
                    say("Try again.")
                else:
                    looped = True
            recognize(text)
        elif text == "goodbye":
            return

def recognize(text):
    print(text)
    commands = {
        "what's the date today" : date_cmd,
        "store password" : password,
        "get password" : get_password,
        "open" : open_web,
        "calculator" : calculate,
        "calculate" : calculate,
        "say" : say_cmd
    }
    while True:
        try:
            commands[text]()
            return
        except:
            say(text + " is not a valid command. Try again")
            text = record_text()

def date_cmd():
    say("Today is " + date.today().strftime("%B %d, %Y"))

def password():
    say("What is the password for?")
    while True:
        usage = record_text()
        satis = record_text("is " + usage + " what you said?")
        if satis == "yes":
            break
        else:
            say("Try again.")

    while True:
        password = record_text("What is the actual password?")
        satis = record_text("is " + password + " what you said?")
        if satis == "yes":
            break
        else:
            say("Try again.")

    with open("password.txt", "a") as file:
        file.write(usage + " : ")
        key = random.randint(1,10000)
        for i in password:
            file.write(str(ord(i) + key) + ",")
        file.write(str(key) + " \n")

def get_password():
    usage = record_text("which password do you want?")
    password_decoded = ""
    password = False
    password_1 = False
    try:
        with open("password.txt", "r") as file:
            text = file.read()
        for i in text.split(" "):

            if password:
                try:
                    int(i[0])
                except:
                    continue
                password_encoded = i.split(",")
                key = int(password_encoded[len(password_encoded) - 1])
                for j in range(0,len(password_encoded)-1):
                    password_decoded += chr(int(password_encoded[j]) - key)
                print("password: " + password_decoded)
                break

            if len(usage.split(" ")) > 1:
                if password_1 and usage.split(" ")[1] == i.strip():
                    password = True
            else:
                password = True

            if len(usage.split(" ")) > 1:
                if usage.split(" ")[0] == i.strip():
                    password_1 = True
            else:
                if usage == i.strip():
                    password_1 = True
    except FileNotFoundError:
        say("you don't have any passwords")

    say(password_decoded)

def open_web():
    possible = {
        "YouTube" : "https://www.youtube.com",
        "Gmail" : "https://mail.google.com/mail",
        "drive" : "https://drive.google.com/drive/"
    }
    while True:
        to_open = record_text("what do you want to open")

        if to_open in possible:
            webbrowser.open_new_tab(possible[to_open])
            return

        say("Try again.")

def calculate():
    while True:
        equation = record_text("say the equation")
        for i in range(0,len(equation)):
            if equation[i] ==  "x":
                equation = equation[:i] + "*" + equation[i+1:]
        try:
            say("The answer is " + str(eval(equation)))
            return
        except:
            say("wrong equation")

def say_cmd():
    while True:
        text = record_text("what should I say?")
        if text != "Failed":
            say(text)
            return

        say("Try again.")


if __name__ == "__main__":
    main()