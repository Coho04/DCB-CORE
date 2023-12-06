## DCB-CORE
Integrate the Discord Bot Core with new bots for a seamless setup experience
## Installation
        DCBotBuilder dcBotBuilder = new DCBotBuilder(args);
        LinkedList<CommandInterface> commandDataList  = new LinkedList<>();
        commandDataList.add(new CustomCommand());
        dcBotBuilder.registerCommands(commandDataList);
        dcBotBuilder.registerEvents(new CustomEvents());
        DCBot dcBot = dcBotBuilder.build();

## License
This project is licensed under the MIT license.

## Support
If you have problems or questions, you can open an issue on GitHub or contact the developer directly.

