package pba.devops.addressbook.utils;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

public class CommandUtils {

    static public List<String> niceSplit(String rawCommand) {
        return
            Arrays.stream(rawCommand.trim().split(" "))
                .map(part -> part.trim())
                .filter(part -> !StringUtils.isEmpty(part))
                .collect(Collectors.toList());
    }
}
