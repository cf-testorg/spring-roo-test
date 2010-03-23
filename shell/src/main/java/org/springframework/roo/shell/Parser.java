package org.springframework.roo.shell;

import java.util.List;

/**
 * Interface for {@link SimpleParser}.
 * 
 * @author Ben Alex
 * @since 1.0
 *
 */
public interface Parser {

	public abstract ParseResult parse(String buffer);

	public abstract int complete(String buffer, int cursor, List<String> candidates);

}