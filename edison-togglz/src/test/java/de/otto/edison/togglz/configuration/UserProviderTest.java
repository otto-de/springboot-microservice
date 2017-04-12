package de.otto.edison.togglz.configuration;

import org.junit.Test;
import org.springframework.util.Base64Utils;
import org.togglz.core.user.FeatureUser;
import org.togglz.core.user.UserProvider;
import org.togglz.servlet.util.HttpServletRequestHolder;

import javax.servlet.http.HttpServletRequest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserProviderTest {

    @Test
    public void shouldReturnAuthenticatedUser() {

        // given
        final UserProvider userProvider = new TogglzConfiguration().userProvider();

        HttpServletRequest mockRequest = mock(HttpServletRequest.class);
        when(mockRequest.getHeader("Authorization")).thenReturn("Basic " + Base64Utils.encodeToString("testuser:password".getBytes()));

        HttpServletRequestHolder.bind(mockRequest);

        // when
        FeatureUser currentUser = userProvider.getCurrentUser();
        // then
        assertThat(currentUser.getName(), is("testuser"));
    }
}
