package test.ricardo.rpsgame.core.game.domain;

public enum Move {

	ROCK {

		@Override
		boolean beats(Move move) {
			return SCICCORS == move;
		}

	},
	PAPER {

		@Override
		boolean beats(Move move) {
			return ROCK == move;
		}

	},
	SCICCORS {

		@Override
		boolean beats(Move move) {
			return PAPER == move;
		}
	};

	abstract boolean beats(Move move);

}
