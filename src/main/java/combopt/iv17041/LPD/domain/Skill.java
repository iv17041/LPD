package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

//@JsonIdentityInfo(scope = Skill.class, generator = ObjectIdGenerators.PropertyGenerator.class,
//       property = "skillName")
public class Skill {
        private String skillName;

        public Skill() {}

        public Skill(String name) {
            this.setSkillName(name);
        }

        @Override
        public String toString() {
            return this.getSkillName();
        }

        public String getSkillName() {
        return skillName;
    }

        public void setSkillName(String skillName) {
        this.skillName = skillName;
    }
}
