package residentevil.web.controllers.virus;

import residentevil.service.CapitalService;
import residentevil.service.VirusService;
import residentevil.web.controllers.BaseController;
import org.springframework.web.bind.annotation.SessionAttributes;

@SessionAttributes({BaseVirusController.VIRUS, BaseVirusController.CAPITALS})
class BaseVirusController extends BaseController {

    public static final String VIRUS = "virus";
    public static final String VIRUSES = "viruses";
    public static final String CAPITALS = "allCapitals";

    protected final VirusService virusService;
    protected final CapitalService capitalService;

    protected BaseVirusController(VirusService virusService,
                                  CapitalService capitalService) {
        this.virusService = virusService;
        this.capitalService = capitalService;
    }
}
