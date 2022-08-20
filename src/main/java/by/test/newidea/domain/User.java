package by.test.newidea.domain;

import lombok.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@EqualsAndHashCode
@Builder
//@ToString
@NoArgsConstructor
@AllArgsConstructor

public class User  {
    private Long id;
    private String userName;
    private String surname;
    private Timestamp birth;
    private Boolean isDeleted;
    private Timestamp createDate;
    private Timestamp modificationDate;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this,ToStringStyle.JSON_STYLE);
    }
}
