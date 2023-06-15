package rw.ac.rca.webapp.dao;

import rw.ac.rca.webapp.orm.Mark;

import java.util.List;

public interface MarksDAO {
        /**
         *
         */

        /**
         * @author Aphrodice Rwagaju
         */


        public Mark saveMarks(Mark marks);

        public Mark updateMarks(Mark marks);

        public Mark saveOrUpdateMarks(Mark marks);

        public boolean deleteUser(Mark marks);

        public Mark getMarksById(int marksId);

        public List<Mark> getAllMarks();



}