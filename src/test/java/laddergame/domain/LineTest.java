package laddergame.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import laddergame.domain.strategy.CanBuildStrategy;
import laddergame.util.BooleanGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("라인")
public class LineTest {
    @Test
    @DisplayName("라인을 생성하는지 테스트한다.")
    public void createLineTest() {
        //given
        final int personCount = 4;
        final int expectedSize = personCount - 1;

        //when
        Line line = new Line(personCount);

        //then
        assertEquals(line.getPoints().size(), expectedSize);
    }

    @Test
    @DisplayName("랜덤값이 true일 경우 특정 위치에서 다리를 건설한다.")
    public void buildBridgeTest() {
        //given
        final int personCount = 4;
        final int position = 1;
        Line line = new Line(personCount);

        CanBuildStrategy canBuildStrategy = new CanBuildStrategy() {
            @Override
            public List<Boolean> canBuildBridges(int count) {
                return List.of(Boolean.TRUE, Boolean.FALSE, Boolean.TRUE);
            }
        };

        List<Boolean> isBridgeBuilt = canBuildStrategy.canBuildBridges(personCount);

        //when
        line.buildBridge(isBridgeBuilt);

        //then
        assertFalse(line.isBuilt(position));
    }

}
