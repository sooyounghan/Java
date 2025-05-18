package chat.server;

import chat.server.command.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommandManagerV4 implements CommandManager {

    private static final String DELIMITER = "\\|";
    private final Map<String, Command> commands = new HashMap<>();
    private final Command defaultCommand = new DefaultCommand();

    public CommandManagerV4(SessionManager sessionManager) {
        // 각 명령어를 Key, 이에 해당하는 Command 구현체를 Value
        commands.put("/join", new JoinCommand(sessionManager));
        commands.put("/message", new MessageCommand(sessionManager));
        commands.put("/change", new ChangeCommand(sessionManager));
        commands.put("/users", new UsersCommand(sessionManager));
        commands.put("/exit", new ExitCommand());
    }

    @Override
    public void execute(String totalMessage, Session session) throws IOException {

        // /join|{name}
        String[] args = totalMessage.split(DELIMITER);
        String key = args[0]; // command (key)

        // Null-Object Pattern
        // getOrDefault(key, default) : 해당하는 Key가 없으면 Default값으로 설정한 값 호출
        Command command = commands.getOrDefault(key, defaultCommand);// command (key)에 부합하는 Command 구현체 찾기

        command.execute(args, session);
    }
}
