package com.diycomputerscience.refactored;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

import java.io.IOException;
import java.util.Collections;

import junit.framework.Assert;

import org.junit.Test;

public final class ByeCommandTest {
    @Test
    public void byeCommandShouldAcceptZeroParameters() {
        Assert.assertEquals(0, Commands.BYE.getNumberOfArguments());
    }

    @Test
    public void verifyCommandName() {
        Assert.assertEquals("bye", Commands.BYE.getCommandName());
    }

    @Test
    public void shouldRespondWithByeWhenNotLoggedIn() throws IOException {
        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.isLoggedIn()).thenReturn(false);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.BYE.execute(context, Collections.EMPTY_LIST);

        verify(responder).respondWithBye();

        Assert.assertFalse(sessionIsValid);
    }

    @Test
    public void shouldRespondWithByeWithUserNameWhenLoggedIn() throws IOException {
        String user = "Foo";
        SessionContext context = mock(SessionContext.class);
        Responder responder = mock(Responder.class);
        when(context.isLoggedIn()).thenReturn(true);
        when(context.getUserName()).thenReturn(user);
        when(context.getResponder()).thenReturn(responder);

        boolean sessionIsValid = Commands.BYE.execute(context, Collections.EMPTY_LIST);

        verify(responder).respondWithByeToUser(user);

        Assert.assertFalse(sessionIsValid);
    }
}
