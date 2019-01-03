/*
 * Copyright (c) 2019.  @DragonKnightOfBreeze Windea / @微风的龙骑士 风游迩
 * A WindKid who has tamed the proud Ancient Dragon and led the wind of stories and tales.
 */
package windea.exception;

/**
 * 未完成的异常
 */
public class NotImplementedException extends RuntimeException {
	public NotImplementedException() {
		super();
		System.out.println("未完成的代码！");
	}

	public NotImplementedException(String message) {
		super(message);
		System.out.println("未完成的代码！");
	}
}
