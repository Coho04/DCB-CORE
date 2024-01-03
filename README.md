# DCBot

DCBot is a Discord bot that is written in Java and uses the JDA (Java Discord API) library. It is a comprehensive bot that provides various functions to manage and interact with your Discord server.

## Features

- Command processing**: DCBot has a robust command processing system that allows easy addition and removal of commands.
- **Webhook integration**: The bot can send messages to a Discord channel via webhooks.
- **Activity status**: DCBot can display its current status and the number of servers it is connected to in its activity status.
- **Authorization Management**: The bot has a system to check if a user has the necessary permissions to execute certain commands.

## Setup

1. clone the repository to your local machine
2. import the project into your favorite IDE (IntelliJ IDEA recommended).
3. install the necessary dependencies via Maven.
4. create an `.env` file in the root directory of the project and set the `BOT_TOKEN` to the token of your bot. An `.env.example` file is available in the repository to show the structure of the `.env` file.
5. create an instance of `DCBot` and use the `DCBotBuilder` class to configure and start the bot.

## Commands

DCBot comes with several built-in commands:

- `BotStats`: Displays statistics about the bot.
- `BotOwner`: Gives information about the owner of the bot.
- `Donate`: Gives information on how to contribute to the development of the bot.
- `Help`: Gives help information about the commands of the bot.
- `Invite`: Gives an invitation link for the bot.
- `Join`: Allows the bot to join a server.
- `Ping`: Checks the latency of the bot.
- `Restart`: Restarts the bot.
- `Shutdown`: Switches the bot off.

## Contribute

Contributions are welcome! Please feel free to submit a pull request.

## License

This project is licensed under the MIT license. See the LICENSE file for details.

## Support

If you have problems or questions, you can open an issue on GitHub or contact the developer directly.

## Note

Please note that you should not upload the `.env` file to your repository as it contains sensitive information such as your bot token. Instead, you should create an `.env.example` file that shows the structure of the `.env` file but does not contain any actual values.