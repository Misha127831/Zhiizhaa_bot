package com.zhiizhaabot;

import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProcessor {
    private static final Map<String, String> commands = new HashMap<>();

    static {
        // Информация о Chaser
        commands.put("/chaser",
                "**Chaser | 15ml:**\n" +
                        "- Гліцерин: Chaser Саше | 15ml | Код: 4822439244116\n" +
                        "- Підсилювач Смаку: Chaser Standart | 3ml, 5mg | Код: 1326439260150\n" +
                        "- Підсилювач Смаку: Chaser Standart | 3ml, 6.5mg | Код: 4821616111116\n\n" +
                        "**Chaser | 5ml:**\n" +
                        "- Гліцерин: Chaser Саше | 5ml | Код: 4822439244222\n" +
                        "- Підсилювач Смаку - АМП Standart Chaser | 1ml, 5mg  | Код: 1326439860459");


        // Информация о Alchemist
        commands.put("/alchemist",
                "**Alchemist:**\n" +
                        "- Гліцерин: Chaser Саше | 15ml | Код: 4822439244116\n" +
                        "- Гліцерин: Alchemist | 15ml | Код: 4822439244116\n" +
                        "- Підсилювач Смаку: Alchemist Soft | 3ml, 5mg | Код: 4831385610968");

        // Информация о NOVA
        commands.put("/nova",
                "**NOVA:**\n" +
                        "- Гліцерин: Chaser Саше | 15ml | Код: 4822439244116\n" +
                        "- Нікобустер: NOVA | 3ml, 5mg | Код: 4831385611125\n" +
                        "- Нікобустер: NOVA | 3ml, 6.5mg | Код: 4823561333723");

        // Информация о Dead Horse
        commands.put("/deadhorse",
                "**Dead Horse:**\n" +
                        "- Гліцерин - Dead Horse | 15ml | Код: 7841251500009\n" +
                        "- Нікобустер - Dead Horse | 3ml | Код: 47841260700155");

        // Информация о FL350 Mini
        commands.put("/fl350mini",
                "**FL350 Mini:**\n" +
                        "- Гліцерин - FL350 Mini | 8ml | Код: 3000000000137\n" +
                        "- Нікобустер - FL 350 Mini | 1.5ml | Код: 3000000000144");

        // Информация о Flavorlab: FL350, Aroma MAX, Infinity, Lady, M-Cake, P1, PE, Triple
        commands.put("/flavorlab",
                "**Flavorlab: FL350, Aroma MAX, Infinity, Lady, M-Cake, P1, PE, Triple:**\n" +
                        "- Гліцерин - PE 10000 | 15ml | Код: 1000023456784\n" +
                        "- Нікобустер - FL 350 | 3ml | Код: 2065294820332");

        // Информация о Flavorlab Disposable Puff
        commands.put("/flavorlabdisposablepuff",
                "**Flavorlab Disposable Puff:**\n" +
                        "- Гліцерин - Flavorlab Disposable Puff | 5ml | Код: 9887456321450\n" +
                        "- Нікобустер - FlavorLab | 1ml | Код: 2043537129552");

        // Информация о Fluffy Puff
        commands.put("/fluffypuff",
                "**Fluffy Puff:**\n" +
                        "**12 ml :**\n" +
                        "- Гліцерин - Fluffy Puff | 15ml | Код: 9000044918015\n" +
                        "- Нікотин - Fluffy Puff Salt STRONG 500+ | 3ml | Код: 900073491503\n" +
                        "**6 ml :**\n" +
                        "- Гліцерин - Fluffy Puff | 7.5ml | Код: 9000044918075\n\n" +
                        "- Нікотин - Fluffy Puff Salt 500 | 1.5ml | Код: 9000063491501\n" +
                        "**Organic :**\n" +
                        "- Гліцерин - Fluffy Puff | 40ml | Код: 9000034918040\n" +
                        "- Нікотин - Fluffy Puff 100 | 1.8ml | Код: 900053491100");

        // Информация о Octobar
        commands.put("/octobar",
                "**Octobar:**\n" +
                        "**Octobar | 5ml :**\n" +
                        "- Гліцерин - Octobar | 5ml | Код: 4820000017930\n" +
                        "- Нікобустер - Octobar | 1ml | Код: 4820000017947\n" +
                        "**Octobar | 7,5ml :**\n" +
                        "- Гліцерин - Octobar | 7ml | Код: 4820000018500\n\n" +
                        "- Нікобустер - Octobar | 1.5ml | Код: 4820000018517\n" +
                        "**Octobar | 15ml :**\n" +
                        "- Гліцерин - Octobar | 12ml | Код: 4820000016810\n" +
                        "- Нікобустер - Octobar | 3ml | Код: 4820000016919\n" +
                        "- Нікобустер - Octobar | 3ml Hard");

        // Информация о Wick & Wire, Flip, Twesst
        commands.put("/wickwire",
                "**Wick & Wire, Flip, Twesst:**\n" +
                        "- Гліцерин - Wick & Wire | 15ml | Код: 7840051500004\n" +
                        "- Нікобустер - Wick & Wire | 3ml | Код: 7840060700150");

        // Информация о Lucky
        commands.put("/lucky",
                "**Lucky:**\n" +
                        "- Гліцерин - Wick & Wire | 15ml | Код: 7840051500004\n" +
                        "- Нікобустер - Lucky | 3,75ml | Код: 7840560700155");

        // Информация о Vape Shot
        commands.put("/vapeshot",
                "**Vape Shot:**\n" +
                        "- Гліцерин - Vape Shot | 10ml | Код: 7830151000009\n" +
                        "- Нікобустер - Vape Shot | 50mg | Код: 7840960200156");

        // Информация о Punch 7ml
        commands.put("/punch7ml",
                "**Punch 7ml:**\n" +
                        "- Гліцерин Дистильований - Punch | 6,5ml | Код: 2000000027395\n" +
                        "- Нікобустер - Punch Salt | 1,5ml (5%) | Код: 2000000037363");

        // Информация о Punch 14ml
        commands.put("/punch14ml",
                "**Punch 14ml:**\n" +
                        "- Гліцерин Дистильований - Punch | 13ml | Код: 2000000027401\n" +
                        "- Нікобустер - Punch Salt | 3ml (5%) | Код: 2000000037356\n" +
                        "- Нікобустер - Punch Salt | 3,9ml (6,5%) | Код: 2000000037295");

        // Информация о SteamPuff, Refrost
        commands.put("/steampuff",
                "**SteamPuff, Refrost:**\n" +
                        "- Гліцерин Харчовий Фасований - SteamPuff | 15ml | Код: 2000075392718\n" +
                        "- Компонент Нікотиновий - SteamPuff | 4ml | Код: 2000075392749");

        // Информация о Marvellous, Flamingo, Summer Vibes (7ml)
        commands.put("/marvellous7ml",
                "**Marvellous, Flamingo, Summer Vibes (7ml):**\n" +
                        "- Гліцерин Фармацевтичний - Marvellous | 6ml | Код: 9558754005235\n" +
                        "- Нікотиновий Бустер - Marvellous | 1.5ml 50mg | Код: 3000000000465");

        // Информация о Marvellous, Flamingo, Summer Vibes (15ml)
        commands.put("/marvellous15ml",
                "**Marvellous, Flamingo, Summer Vibes (15ml):**\n" +
                        "- Гліцерин Фармацевтичний - Marvellous | 15ml | Код: 9558754000018\n" +
                        "- Нікотиновий Бустер - Marvellous | 3ml 50mg | Код: 9558754000025");

        // Информация о InBottle
        commands.put("/inbottle",
                "**InBottle:**\n" +
                        "- Гліцерин Та Нікобустер - InBottle | 15ml | Код: 9887456321450");
    }

    public static String getCommandResponse(String command) {
        return commands.getOrDefault(command, "❌ Команда не найдена. Попробуйте /list для полного меню.");
    }

    public static String getCategories() {
        return "Выберите категорию:\n" +
                    "   1️⃣ /chaser — Инфо о Chaser\n" +
                    "   2️⃣ /alchemist — Инфо о Alchemist\n" +
                    "   3️⃣ /nova — Инфо о NOVA\n" +
                    "   4️⃣ /deadhorse — Инфо о Dead Horse\n" +
                    "   5️⃣ /fl350mini — Инфо о FL350 Mini\n" +
                    "   6️⃣ /flavorlab — Инфо о Flavorlab\n" +
                    "   7️⃣ /flavorlabdisposablepuff — Инфо о Disposable Puff\n" +
                    "   8️⃣ /fluffypuff — Инфо о Fluffy Puff\n" +
                    "   9️⃣ /octobar — Инфо о Octobar\n" +
                    "   🔟 /wickwire — Инфо о Wick & Wire\n" +
                    "   1️⃣1️⃣ /lucky — Инфо о Lucky\n" +
                    "   1️⃣2️⃣ /vapeshot — Инфо о Vape Shot\n" +
                    "   1️⃣3️⃣ /punch7ml — Инфо о Punch 7ml\n" +
                    "   1️⃣4️⃣ /punch14ml — Инфо о Punch 14ml\n" +
                    "   1️⃣5️⃣ /marvellous7ml — Инфо о Flamingo 7ml\n" +
                    "   1️⃣6️⃣ /marvellous15ml — Инфо о Flamingo 15ml\n" +
                    "   1️⃣7️⃣ /steampuff — Инфо о SteamPuff\n" +
                    "   1️⃣8️⃣ /inbottle — Инфо о InBottle";
        }

    }