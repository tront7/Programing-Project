
package tvseriesapp;

// Imports updated for JUnit 4
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


public class SeriesTest {

    private Series seriesManager;

    // Annotation changed from @BeforeEach to @Before for JUnit 4
    // Method made 'public' as required by JUnit 4
    @Before
    public void setUp() {
        seriesManager = new Series();
        
        SeriesModel testSeries1 = new SeriesModel();
        testSeries1.seriesId = "SER001";
        testSeries1.seriesName = "Cosmic Journeys";
        testSeries1.seriesAgeRestriction = 12;
        testSeries1.seriesNumberOfEpisodes = 8;
        
        SeriesModel testSeries2 = new SeriesModel();
        testSeries2.seriesId = "SER002";
        testSeries2.seriesName = "The Last Kingdom";
        testSeries2.seriesAgeRestriction = 18;
        testSeries2.seriesNumberOfEpisodes = 46;

        seriesManager.addSeriesForTesting(testSeries1);
        seriesManager.addSeriesForTesting(testSeries2);
    }

    // Method made 'public'
    @Test
    public void testFindSeriesById_WhenExists_ShouldReturnSeries() {
        Optional<SeriesModel> foundSeries = seriesManager.findSeriesById("SER001");
        assertTrue("Series SER001 should be found.", foundSeries.isPresent());
        assertEquals("The series name should match.", "Cosmic Journeys", foundSeries.get().seriesName);
    }
    
    // Method made 'public'
    @Test
    public void testFindSeriesById_WithDifferentCase_ShouldReturnSeries() {
        Optional<SeriesModel> foundSeries = seriesManager.findSeriesById("ser001");
        assertTrue("Series should be found regardless of case.", foundSeries.isPresent());
    }

    // Method made 'public'
    @Test
    public void testFindSeriesById_WhenNotExists_ShouldReturnEmpty() {
        Optional<SeriesModel> foundSeries = seriesManager.findSeriesById("SER999");
        assertFalse("A non-existent series should not be found.", foundSeries.isPresent());
    }

    // Method made 'public'
    @Test
    public void testIsAgeRestrictionValid_WithValidAge_ShouldReturnTrue() {
        assertTrue("Age 12 should be valid.", seriesManager.isAgeRestrictionValid("12"));
    }

    // Method made 'public'
    @Test
    public void testIsAgeRestrictionValid_WithBoundaryAges_ShouldReturnTrue() {
        assertTrue("Lower boundary age 2 should be valid.", seriesManager.isAgeRestrictionValid("2"));
        assertTrue("Upper boundary age 18 should be valid.", seriesManager.isAgeRestrictionValid("18"));
    }

    // Method made 'public'
    @Test
    public void testIsAgeRestrictionValid_WithOutOfRangeAges_ShouldReturnFalse() {
        assertFalse("Age 1 is below the valid range.", seriesManager.isAgeRestrictionValid("1"));
        assertFalse("Age 19 is above the valid range.", seriesManager.isAgeRestrictionValid("19"));
    }

    // Method made 'public'
    @Test
    public void testIsAgeRestrictionValid_WithNonNumericInput_ShouldReturnFalse() {
        assertFalse("Non-numeric input should be invalid.", seriesManager.isAgeRestrictionValid("abc"));
        assertFalse("Empty input should be invalid.", seriesManager.isAgeRestrictionValid(""));
    }
    
    // Method made 'public'
    @Test
    public void testDeleteSeriesLogic() {
        assertTrue("Series should exist before deletion.", seriesManager.findSeriesById("SER002").isPresent());
        
        String idToDelete = "SER002";
        seriesManager.getSeriesList().removeIf(s -> s.seriesId.equalsIgnoreCase(idToDelete));
        
        assertFalse("Series should not exist after deletion.", seriesManager.findSeriesById("SER002").isPresent());
    }
}