package com.faraz.finance.repository.FGNR.personPost;

import java.util.List;

public interface CustomPersonPostRepository {
    void deletePrevPosts(Long personId, List<Short> deletedOrganIdList);

    void deletePrevPosts(Long personId);


}
