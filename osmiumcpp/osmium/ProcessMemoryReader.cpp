#include "ProcessMemoryReader.h"

    ProcessMemoryReader::ProcessMemoryReader() {
        this->coordinates = { 0, 0, 0 };
    }

    void ProcessMemoryReader::PrintCooridnates() {
        cout << "[debuginfo]: " << this->coordinates.x << ", " << this->coordinates.y << ", " << this->coordinates.z << endl;
    }

    std::string ProcessMemoryReader::WcharString(wchar_t* wc)
    {
        string str;
        while (*wc)
            str += (char)*wc++;
        return  str;
    }

    HWND ProcessMemoryReader::GetHandle(std::string title)
    {
        for (HWND hwnd = GetTopWindow(NULL); hwnd != NULL; hwnd = GetNextWindow(hwnd, GW_HWNDNEXT))
        {
            if (!IsWindowVisible(hwnd))
                continue;

            int length = GetWindowTextLength(hwnd);
            if (length == 0)
                continue;

            wchar_t* title_w = new wchar_t[length + 1];

            GetWindowText(hwnd, title_w, length + 1);

            string title_s = WcharString(title_w);

            if (title_s.find(title) != string::npos) {
                return hwnd;
            }
        }
        return NULL;
    }

    uintptr_t ProcessMemoryReader::GetModuleBaseAddress(const wchar_t* lpsz_module_name, DWORD pID) {
        uintptr_t dw_module_base_address = 0;
        HANDLE h_snapshot = CreateToolhelp32Snapshot(TH32CS_SNAPMODULE | TH32CS_SNAPMODULE32, pID);
        MODULEENTRY32 module_entry_32 = { 0 };
        module_entry_32.dwSize = sizeof(MODULEENTRY32);

        if (h_snapshot == INVALID_HANDLE_VALUE) {
            cout << "[error]: Module Snapshot error: Error " << GetLastError() << endl;
            system("PAUSE");
            exit(1);
        }

        if (Module32First(h_snapshot, &module_entry_32))
        {
            do {
                if (_tcscmp(module_entry_32.szModule, lpsz_module_name) == 0)
                {
                    dw_module_base_address = reinterpret_cast<uintptr_t>(module_entry_32.modBaseAddr);
                    break;
                }
            } while (Module32Next(h_snapshot, &module_entry_32));


        }

        CloseHandle(h_snapshot);
        return dw_module_base_address;
    }

    int ProcessMemoryReader::FetchCoordinates() {

        HWND h_game_window = GetHandle("Minecraft");

        if (!h_game_window) {

            cout << "[error]: A Minecraft instance needs to be running." << endl;

            system("pause");

            exit(1);
        }

        DWORD pID = NULL;
        GetWindowThreadProcessId(h_game_window, &pID);

        HANDLE process_handle = NULL;
        process_handle = OpenProcess(PROCESS_ALL_ACCESS, FALSE, pID);

        uintptr_t game_base_address = GetModuleBaseAddress(L"OpenAL.dll", pID);
        uintptr_t offset_game_base_address = 0xFEC38;
        std::vector<DWORD> x_offsets{ 0x8, 0x0 };
        std::vector<DWORD> y_offsets{ 0x8, 0x4 };
        std::vector<DWORD> z_offsets{ 0x8, 0x8 };

        uintptr_t base_address = NULL;

        ReadProcessMemory(process_handle, (LPVOID)(game_base_address + offset_game_base_address), &base_address, sizeof(base_address), NULL);

        uintptr_t x_address = base_address;
        uintptr_t y_address = base_address;
        uintptr_t z_address = base_address;

        for (int i = 0; i < x_offsets.size() - 1; i++)
        {
            ReadProcessMemory(process_handle, (LPVOID)(x_address + x_offsets.at(i)), &x_address, sizeof(x_address), NULL);
            ReadProcessMemory(process_handle, (LPVOID)(y_address + y_offsets.at(i)), &y_address, sizeof(y_address), NULL);
            ReadProcessMemory(process_handle, (LPVOID)(z_address + z_offsets.at(i)), &z_address, sizeof(z_address), NULL);
        }
        x_address += x_offsets.at(x_offsets.size() - 1);
        y_address += y_offsets.at(y_offsets.size() - 1);
        z_address += z_offsets.at(z_offsets.size() - 1);

        ReadProcessMemory(process_handle, (LPVOID)(x_address), &coordinates.x, sizeof(coordinates.x), 0);
        ReadProcessMemory(process_handle, (LPVOID)(y_address), &coordinates.y, sizeof(coordinates.y), 0);
        ReadProcessMemory(process_handle, (LPVOID)(z_address), &coordinates.z, sizeof(coordinates.z), 0);

        return 0;
    }

    Coordinates ProcessMemoryReader::GetCoordinates() {
        return coordinates;
    }
