package residentevil.domain.entities;

import lombok.NoArgsConstructor;
import residentevil.domain.converters.CreatorConverter;
import residentevil.domain.converters.MagnitudeConverter;
import residentevil.domain.enums.Creator;
import residentevil.domain.enums.Magnitude;
import residentevil.domain.enums.Mutation;
import residentevil.domain.validation.annotations.composite.virus.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Entity
@Table(name = "viruses")
public class Virus extends BaseUuidEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ValidVirusName
    @Column(unique = true, nullable = false, length = ValidVirusName.MAX_LENGTH)
    private String name;

    @ValidVirusDescription
    @Column(nullable = false, columnDefinition = "TEXT", length = ValidVirusDescription.MAX_LENGTH)
    private String description;

    @ValidVirusSideEffects
    @Column(length = ValidVirusSideEffects.MAX_LENGTH)
    private String sideEffects;

    @ValidVirusCreator
    @Convert(converter = CreatorConverter.class)
    @Column(nullable = false, length = ValidVirusCreator.MAX_LENGTH)
    private Creator creator;

    @ValidVirusIsDeadly
    @Column(nullable = false)
    private Boolean isDeadly;

    @ValidVirusIsCurable
    @Column(nullable = false)
    private Boolean isCurable;

    @ValidVirusMutation
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = ValidVirusMutation.MAX_LENGTH)
    private Mutation mutation;

    @ValidVirusTurnoverRate
    @Column(nullable = false)
    private Integer turnoverRate;

    @ValidVirusHoursUntilMutation
    @Column(nullable = false)
    private Integer hoursUntilMutation;

    @ValidVirusMagnitude
    @Convert(converter = MagnitudeConverter.class)
    @Column(nullable = false, length = ValidVirusMagnitude.MAX_LENGTH)
    private Magnitude magnitude;

    @ValidVirusReleasedOn
    @Column(nullable = false)
    private Date releasedOn;

    @ValidVirusCapitals
    @ManyToMany
    @JoinTable(
            name = "viruses_capitals",
            joinColumns = {@JoinColumn(name = "virus_id")},
            inverseJoinColumns = {@JoinColumn(name = "capital_id")})
    private Set<CapitalId> capitals = new HashSet<>();
}
