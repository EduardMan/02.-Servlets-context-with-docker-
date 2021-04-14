package tech.itpark.proggerhub.security;

import java.util.Arrays;
import java.util.Set;

// TODO: и для Data Class'ов, Model'ей (в том числе Rich Model)
public interface Authentication {
  String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";

  long getId(); // owner -> дискреционный доступ
  Set<String> getRoles(); // -> ролевые

  default boolean hasAnyRoles(String ...roles) {
    return Arrays.stream(roles).anyMatch(o -> getRoles().contains(o));
  }

  default boolean hasAllRoles(String ...roles) {
    return getRoles().containsAll(Arrays.asList(roles));
  }

  static Authentication anonymous() {
    return new Authentication() {
      @Override
      public long getId() {
        return 0;
      }

      @Override
      public Set<String> getRoles() {
        return Set.of(ROLE_ANONYMOUS);
      }
    };
  }
}
