## DCB-CORE
Add Discord Bot Core to new Bots for easy Setup

        DCBotBuilder dcBotBuilder = new DCBotBuilder(args);
        LinkedList<CommandInterface> commandDataList  = new LinkedList<>();
        commandDataList.add(new CustomCommand());
        dcBotBuilder.registerCommands(commandDataList);
        dcBotBuilder.registerEvents(new CustomEvents());
        DCBot dcBot = dcBotBuilder.build();