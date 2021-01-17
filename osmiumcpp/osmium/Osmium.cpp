#include "ProcessMemoryReader.h"
#include <conio.h>
#include <cstdlib>

    using namespace std;

    const string DEFAULT_COMMAND = "java -jar ../../../../osmiumjava/build/libs/Osmium.jar";
    const string DEFAULT_COMMAND_DEBUG = "java -jar C:/Users/Aryad/Desktop/workspacemc/osmium/osmiumjava/build/libs/Osmium.jar";

boolean is_modded = FALSE;

string game_dir;
string game_ver;
string command = DEFAULT_COMMAND_DEBUG;

void SetColor(int color) 
{
    SetConsoleTextAttribute(GetStdHandle(STD_OUTPUT_HANDLE), color);
}

void GoTo(int x, int y) 
{
    COORD c;
    c.X = x;
    c.Y = y;
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), c);
}

void ShowConsoleCursor(bool showFlag)
{
    HANDLE out = GetStdHandle(STD_OUTPUT_HANDLE);

    CONSOLE_CURSOR_INFO     cursorInfo;

    GetConsoleCursorInfo(out, &cursorInfo);
    cursorInfo.bVisible = showFlag; // set the cursor visibility
    SetConsoleCursorInfo(out, &cursorInfo);
}

int PrintSeed() 
{
    if (game_dir.empty()) {
        system(command.c_str());
    }
    else {
        command += " " + game_dir + "/saves/";
        system(command.c_str());
    }

    return 0;
}

int main() 
{
    ProcessMemoryReader pmr;

    ShowConsoleCursor(false);

    int Set[] = { 12, 7, 7 };
    int counter = 1;
    char key;

    for (;;) {
        system("CLS");

        GoTo(10, 5);
        SetColor(Set[0]);
        cout << "1. Options ";

        GoTo(10, 6);
        SetColor(Set[1]);
        cout << "2. Peak ";

        GoTo(10, 7);
        SetColor(Set[2]);
        cout << "3. Exit ";

        key = _getch();

        if (key == 72 && (counter >= 2 && counter <= 3)) {
            counter--;
        }

        if (key == 80 && (counter >= 1 && counter <= 2)) {
            counter++;
        }

        if (key == '\r') {
            if (counter == 1) {
                system("CLS");
                }
            else if (counter == 2) {
                system("CLS");
                for (;;) {
                    GoTo(10, 5);
                    cout << "Your coordinates are: ";
                    GoTo(10, 6);
                    pmr.FetchCoordinates();
                    Coordinates coordinates = pmr.GetCoordinates();
                    cout << coordinates.x << ", " << coordinates.y << ", " << coordinates.z << "\r" << endl << flush;

                    GoTo(10, 8);
                    cout << "Nearest stronghold: ";
                    GoTo(10, 9);
                    PrintSeed();

                    if (GetAsyncKeyState(VK_ESCAPE)) {
                        break;
                    }
                }
            }
            else if (counter == 3) {
                exit(0);
            }
        }

        Set[0] = 7;
        Set[1] = 7;
        Set[2] = 7;

        if (counter == 1) {
            Set[0] = 12;
        }
        if (counter == 2) {
            Set[1] = 12;
        }
        if (counter == 3) {
            Set[2] = 12;
        }
    }
}