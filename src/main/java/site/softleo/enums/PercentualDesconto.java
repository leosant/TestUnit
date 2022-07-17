package site.softleo.enums;

import site.softleo.domains.Filme;
import site.softleo.domains.Locacao;

import java.math.BigDecimal;

public enum PercentualDesconto {

    DESC25(3,
            0.25
    ),
    DESC50(4,
            0.5
    ),
    DESC75(5,
            0.75
    ),
    DESC100(6,
            1.0
    );

    PercentualDesconto(int qtdFilmes, double percentual) {
        this.qtdFilmes = qtdFilmes;
        this.percentual = percentual;
    }

    private int qtdFilmes;
    private double percentual;

    public static double calculaPercentualDesconto(int size) {

        double percentual = 0.0;

        for (PercentualDesconto desc : PercentualDesconto.values()) {
            if (desc.qtdFilmes == size) {
                percentual = desc.percentual;
                break;
            }
        }

        return percentual;
    }
}
