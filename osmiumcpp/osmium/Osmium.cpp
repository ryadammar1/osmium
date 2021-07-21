#include "ProcessMemoryReader.h"
#include "JavaInvocater.h"
#include <conio.h>
#include <cstdlib>
#include <vector>

using namespace std;

const string DEFAULT_LIBRARY_PATH = "./libs/Osmium.jar";
string game_dir;
string blank = "                          ";

JavaInvocater* javaInvocater;

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
    cursorInfo.bVisible = showFlag;
    SetConsoleCursorInfo(out, &cursorInfo);
}

int UpdateGameDirectory() {
    javaInvocater->UpdateGameDir(game_dir);

    return 0;
}

int PrintStronghold()
{
    vector<int> coords = javaInvocater->CallJar("STRONGHOLD", 0, 0);
    cout << coords[1] << ", " << coords[2] << blank;

    return 0;
}

int PrintBastion(int x, int z)
{
    vector<int> coords = javaInvocater->CallJar("BASTION", x, z);
    cout << coords[1] << ", " << coords[2] << blank;

    return 0;
}

int PrintFortress(int x, int z)
{
    vector<int> coords = javaInvocater->CallJar("FORTRESS", x, z);
    cout << coords[1] << ", " << coords[2] << blank;

    return 0;
}

int main() 
{
    ProcessMemoryReader pmr;
    javaInvocater = new JavaInvocater(DEFAULT_LIBRARY_PATH);

    ShowConsoleCursor(false);

    int Menu1[] = { 12, 7, 7 };
    int Menu2[] = { 12, 7 };
    int counter1 = 1;
    int counter2 = 1;
    char key;

    for (;;) {
        system("CLS");

        GoTo(10, 5);
        SetColor(Menu1[0]);
        cout << "Peek";

        GoTo(10, 7);
        SetColor(Menu1[1]);
        cout << "Options ";

        GoTo(10, 9);
        SetColor(Menu1[2]);
        cout << "Exit ";

        key = _getch();

        if ((key == 80 || key == 's' || 
            key == 'S') && (counter1 == 3)) {
            counter1 = 1;
        }

        else if ((key == 72 || key == 'w' || 
            key == 'W') && (counter1 == 1)) {
            counter1 = 3;
        }

        else if ((key == 72 || key == 'w' || 
            key == 'W') && (counter1 >= 2 && counter1 <= 3)) {
            counter1--;
        }

        else if ((key == 80 || key == 's' || 
            key == 'S') && (counter1 >= 1 && counter1 <= 2)) {
            counter1++;
        }

        if (key == '\r') {
            if (counter1 == 2) {
                system("CLS");
                for (;;) {
                    system("CLS");

                    GoTo(10, 5);
                    SetColor(Menu2[0]);
                    cout << "Change game directory";

                    GoTo(10, 7);
                    SetColor(Menu2[1]);
                    cout << "Reset game directory";

                    key = _getch();

                    if (((key == 80 || key == 's' || key == 'S') || 
                        (key == 72 || key == 'w' || key == 'W')) && (counter2 == 2)) {
                        counter2 = 1;
                    }

                    else if (((key == 80 || key == 's' || key == 'S') || 
                        (key == 72 || key == 'w' || key == 'W')) && (counter2 == 1)) {
                        counter2 = 2;
                    }

                    if (key == '\r') {
                        if (counter2 == 1) {
                            system("CLS");
                            SetColor(7);
                            GoTo(10, 5);
                            cout << "Past game directory here: ";
                            getline(cin, game_dir);
                        }
                        else if (counter2 == 2) {
                            game_dir = "default";
                        }

                        UpdateGameDirectory();
                    }

                    if (key == 27)
                        break;

                    Menu2[0] = 7;
                    Menu2[1] = 7;

                    if (counter2 == 1) {
                        Menu2[0] = 12;
                        }
                    if (counter2 == 2) {
                        Menu2[1] = 12;
                        }
                    }
                }
            else if (counter1 == 1) {
                system("CLS");
                for (;;) {
                    GoTo(10, 5);
                    cout << "Your coordinates are: ";
                    GoTo(10, 6);
                    if (pmr.FetchCoordinates())
                        break;
                    Coordinates coordinates = pmr.GetCoordinates();
                    cout << (int) coordinates.x << ", " << (int) coordinates.y << ", " << (int) coordinates.z 
                        << blank << endl << flush;

                    GoTo(10, 8);
                    cout << "Nearest stronghold: ";
                    GoTo(10, 9);
                    PrintStronghold();

                    GoTo(10, 11);
                    cout << "Nearest bastion: ";
                    GoTo(10, 12);
                    PrintBastion((int)coordinates.x, (int)coordinates.z);

                    GoTo(10, 14);
                    cout << "Nearest fortress: ";
                    GoTo(10, 15);
                    PrintFortress((int)coordinates.x, (int)coordinates.z);

                    if (_kbhit()) {
                        key = _getch();
                        if (key == 27)
                            break;
                    }
                }
            }
            else if (counter1 == 3) {
                exit(0);
            }
        }

        Menu1[0] = 7;
        Menu1[1] = 7;
        Menu1[2] = 7;

        if (counter1 == 1) {
            Menu1[0] = 12;
        }
        if (counter1 == 2) {
            Menu1[1] = 12;
        }
        if (counter1 == 3) {
            Menu1[2] = 12;
        }
    }
}