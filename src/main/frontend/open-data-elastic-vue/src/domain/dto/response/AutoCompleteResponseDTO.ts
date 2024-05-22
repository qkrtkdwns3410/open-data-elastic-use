/**
 *packageName    :
 * fileName       : AutoCompleteResponseDTO
 * author         : ipeac
 * date           : 24. 5. 5.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 5. 5.        ipeac       최초 생성
 */
class AutoCompleteResponseDTO {
    private readonly _movieNm: string;
    private readonly _movieNmEn: string;

    private constructor(movieNm: string, movieNmEn: string) {
        this._movieNm = movieNm;
        this._movieNmEn = movieNmEn
    }

    get movieNm(): string {
        return this._movieNm;
    }

    get movieNmEn(): string {
        return this._movieNmEn;
    }

    public static of(movieNm: string, movieNmEn: string): AutoCompleteResponseDTO {
        return new AutoCompleteResponseDTO(movieNm, movieNmEn);
    }
}

export {AutoCompleteResponseDTO}